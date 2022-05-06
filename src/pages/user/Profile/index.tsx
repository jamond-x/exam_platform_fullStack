import React, { useState } from 'react';
import style from './index.less';
import { Avatar, Card, Image, Badge, Table, Form, Input, Button, Select } from 'antd';
import { FormOutlined } from '@ant-design/icons';
import { useModel } from 'umi';
import { updateInfo } from '@/services/request/api';

const Profile: React.FC = () => {
  const { initialState, setInitialState } = useModel('@@initialState');
  const [modify, setModify] = useState(false);
  console.log(initialState);

  const columns = [
    {
      title: '',
      dataIndex: 'key',
      key: 'key',
    },
    {
      title: '',
      dataIndex: 'value',
      key: 'value',
    },
  ];

  const data = [
    {
      key: '个性签名',
      value: initialState?.currentUser?.description || '这个人很懒没有留下签名~',
    },
    {
      key: '性别',
      value: initialState?.currentUser?.gender || '未知',
    },
    {
      key: 'Email',
      value: initialState?.currentUser?.email || '保密',
    },
  ];

  const onFinish = async (values: { name: string; description: string; email: string }) => {
    const res = await updateInfo({
      ...values,
      gender: initialState?.currentUser?.gender,
      id: initialState?.currentUser?.id,
    });
    if (res.code === '0' && res.data != null) {
      const userInfo: API.CurrentUser | undefined = await initialState?.fetchUserInfo?.();
      if (userInfo) {
        await setInitialState((s) => ({
          ...s,
          currentUser: userInfo,
        }));
      }
    }
  };

  return (
    <div className={style.container}>
      <Card
        className={style.card}
        title="账户信息"
        extra={
          <a href="#" onClick={() => setModify(!modify)}>
            <FormOutlined />
            修改
          </a>
        }
        style={{ width: 700 }}
      >
        <div className={style.inside}>
          <Avatar size={150} src={<Image src={initialState?.currentUser?.avatar} />} />
          <div>
            <div className={style.name}>
              {initialState?.currentUser?.name}
              {initialState?.currentUser?.admin === '1' && (
                <Badge
                  className="site-badge-count-109"
                  count={'管理员'}
                  style={{ backgroundColor: '#52c41a' }}
                />
              )}
            </div>
            {!modify && <Table columns={columns} dataSource={data} />}
            {modify && (
              <Form
                name="basic"
                labelCol={{ span: 4 }}
                wrapperCol={{ span: 16 }}
                onFinish={onFinish}
                autoComplete="off"
                className={style.mt}
              >
                <Form.Item
                  label="姓名"
                  name="name"
                  initialValue={initialState?.currentUser?.name}
                  rules={[{ required: true, message: '请输入你的姓名！' }]}
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  label="Email"
                  name="email"
                  initialValue={initialState?.currentUser?.email}
                  rules={[{ required: true, message: '请输入你的电子邮箱！' }]}
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  label="头像链接"
                  name="avatar"
                  initialValue={initialState?.currentUser?.avatar}
                  rules={[{ required: true, message: '请输入你的头像链接' }]}
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  label="个性签名"
                  name="description"
                  initialValue={initialState?.currentUser?.description}
                  rules={[{ required: true, message: '请输入你的描述！' }]}
                >
                  <Input />
                </Form.Item>

                <Form.Item wrapperCol={{ offset: 4, span: 16 }}>
                  <Button type="primary" htmlType="submit">
                    提交
                  </Button>
                </Form.Item>
              </Form>
            )}
          </div>
        </div>
      </Card>
    </div>
  );
};

export default Profile;
