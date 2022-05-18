import type { Settings as LayoutSettings } from '@ant-design/pro-layout';
import { SettingDrawer } from '@ant-design/pro-layout';
import { PageLoading } from '@ant-design/pro-layout';
import type { RunTimeLayoutConfig } from 'umi';
import { history } from 'umi';
import RightContent from '@/components/RightContent';
import Footer from '@/components/Footer';
// import { currentUser as queryCurrentUser } from './services/ant-design-pro/api';
import { currentUser, currentUser as queryCurrentUser, verifyToken } from './services/request/api';
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
      const token = window.localStorage.getItem('token');
      // if  没有登录信息 且 不在登录页面
      // 跳转到登录页面
      if (!initialState?.currentUser && !token && location.pathname !== loginPath) {
        history.push(loginPath);
        return;
      }
      // if  有登录信息：账户信息 或 token
      // if  有账户信息 且 在登录页 那么跳转至欢迎页
      // if  没有账户信息 但有 token  那么自动验证token 有效去欢迎页 无则去登录页
      else if (initialState?.currentUser && location.pathname === loginPath) {
        history.push('/welcome');
        return;
      } else if (!initialState?.currentUser && token) {
        const res = await verifyToken(token as string);
        if (res && res.code === '0') {
          const user = await currentUser(res.data?.id as string);
          await setInitialState((s) => ({
            ...s,
            currentUser: user.data,
          }));
          if (location.pathname === loginPath) {
            history.push('/welcome');
          }
          return;
        } else {
          if (location.pathname != loginPath) {
            history.push(loginPath);
          }
          return;
        }
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
