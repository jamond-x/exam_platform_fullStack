import React from 'react';
import style from './index.less';
import { Avatar, Card, Image } from 'antd';
import { useModel } from 'umi';

const Profile: React.FC = () => {
  const { initialState, setInitialState } = useModel('@@initialState');
  console.log(initialState.currentUser);

  return (
    <div className={style.container}>
      <Card
        className={style.card}
        title="账户信息"
        extra={<a href="#">More</a>}
        style={{ width: 700 }}
      >
        <div className={style.inside}>
          <Avatar size={150} src={<Image src={initialState.currentUser.avatar} />} />
          <div className={style.name}>{initialState.currentUser.name}</div>
        </div>
      </Card>
    </div>
  );
};

export default Profile;
