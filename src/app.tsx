import type { Settings as LayoutSettings } from '@ant-design/pro-layout';
import { SettingDrawer } from '@ant-design/pro-layout';
import { PageLoading } from '@ant-design/pro-layout';
import type { RunTimeLayoutConfig } from 'umi';
import { history, Link } from 'umi';
import RightContent from '@/components/RightContent';
import Footer from '@/components/Footer';
// import { currentUser as queryCurrentUser } from './services/ant-design-pro/api';
import { currentUser, currentUser as queryCurrentUser, verifyToken } from './services/request/api';
import { BookOutlined, LinkOutlined } from '@ant-design/icons';
import defaultSettings from '../config/defaultSettings';

const isDev = process.env.NODE_ENV === 'development';
const loginPath = '/user/login';

/** 获取用户信息比较慢的时候会展示一个 loading */
export const initialStateConfig = {
  loading: <PageLoading />,
};

/**
 * @see  https://umijs.org/zh-CN/plugins/plugin-initial-state
 * */
export async function getInitialState(): Promise<{
  settings?: Partial<LayoutSettings>;
  currentUser?: API.CurrentUser;
  loading?: boolean;
  userId?: string | null;
  setUserId?: (id: string) => void;
  fetchUserInfo?: () => Promise<API.CurrentUser | undefined>;
}> {
  let userId: string | undefined = undefined;
  const setUserId = (id: string | undefined) => {
    userId = id;
  };

  const fetchUserInfo = async () => {
    try {
      if (userId === undefined) {
        history.push(loginPath);
        return undefined;
      }
      const msg = await queryCurrentUser(userId as string); // 这里存一下返回的用户信息
      return msg.data as API.CurrentUser;
    } catch (error) {
      history.push(loginPath);
    }
    return undefined;
  };
  // 如果不是登录页面，执行
  if (history.location.pathname !== loginPath) {
    const currentUser = (await fetchUserInfo()) as API.CurrentUser;

    return {
      fetchUserInfo,
      currentUser,
      settings: defaultSettings,
    };
  }
  return {
    fetchUserInfo,
    settings: defaultSettings,
    userId,
    setUserId,
  };
}

// ProLayout 支持的api https://procomponents.ant.design/components/layout
export const layout: RunTimeLayoutConfig = ({ initialState, setInitialState }) => {
  return {
    rightContentRender: () => <RightContent />,
    disableContentMargin: false,
    waterMarkProps: {
      content: initialState?.currentUser?.name,
    },
    footerRender: () => <Footer />,
    onPageChange: async () => {
      const { location } = history;
      // 如果没有登录，重定向到 login
      const token = window.localStorage.getItem('token');
      if (initialState?.currentUser || token) {
        console.log(4444444444);
        history.push('/');
      } else if (!initialState?.currentUser && !token && location.pathname !== loginPath) {
        console.log(111111);

        history.push(loginPath);
        return;
      } else if (!initialState?.currentUser && token) {
        // TODO:自动验证token
        const res = await verifyToken(token as string);
        if (res.code === '0') {
          const user = await currentUser(res.data?.id as string);
          if (user.code === '0') {
            await setInitialState((s) => ({
              ...s,
              currentUser: user.data,
            }));
            console.log(222222);
            history.push('/'); // 死循环了 ！！！！
          }
        } else {
          console.log(333333);
          history.push(loginPath);
        }
        return;
      }
    },
    links: isDev
      ? []
      : // ? [
        //     <Link key="openapi" to="/umi/plugin/openapi" target="_blank">
        //       <LinkOutlined />
        //       <span>OpenAPI 文档</span>
        //     </Link>,
        //     <Link to="/~docs" key="docs">
        //       <BookOutlined />
        //       <span>业务组件文档</span>
        //     </Link>,
        //   ]
        [],
    menuHeaderRender: undefined,
    // 自定义 403 页面
    // unAccessible: <div>unAccessible</div>,
    // 增加一个 loading 的状态
    childrenRender: (children, props) => {
      // if (initialState?.loading) return <PageLoading />;
      return (
        <>
          {children}
          {!props.location?.pathname?.includes('/login') && (
            <SettingDrawer
              disableUrlParams
              enableDarkTheme
              settings={initialState?.settings}
              onSettingChange={(settings) => {
                setInitialState((preInitialState) => ({
                  ...preInitialState,
                  settings,
                }));
              }}
            />
          )}
        </>
      );
    },
    ...initialState?.settings,
  };
};
