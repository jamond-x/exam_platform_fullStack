import React, { useState, useEffect } from 'react';
import { Table } from 'antd';
import { allUsers } from '@/services/request/api';

const AllUser: React.FC = () => {
  const [tableData, setTableData] = useState<API.CurrentUser[] | undefined>(undefined);
  const columns = [
    {
      title: 'id',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: '姓名',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '性别',
      dataIndex: 'gender',
      key: 'gender',
    },
    {
      title: '管理员',
      dataIndex: 'admin',
      key: 'admin',
    },
    {
      title: 'Email',
      dataIndex: 'email',
      key: 'email',
    },
  ];

  useEffect(() => {
    const getAllUser = async () => {
      const res = await allUsers();
      console.log(res);
      setTableData(res.data);
    };
    getAllUser();
  }, []);

  return (
    <>
      <h1>所有账号信息</h1>
      <Table columns={columns} dataSource={tableData} />
    </>
  );
};

export default AllUser;
