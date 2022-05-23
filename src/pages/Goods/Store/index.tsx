import React, { useEffect, useState } from 'react';
import { Card, Drawer, Button, Image, Badge, message, Form, Input } from 'antd';
import { ShoppingCartOutlined, PlusOutlined } from '@ant-design/icons';
import styles from './index.less';
import { queryGoods } from '@/services/request/api';
import { divide } from 'lodash';

const Store: React.FC = () => {
  // const goods = [
  //   {
  //     id: 'cesdcdvfvdf',
  //     name: '《深入浅出Nodejs》',
  //     description:
  //       '【官方旗舰店】 深入浅出Node.js 扑灵原创 Node.js开发实战详解计算机编程设计web开发Node.js实战node.js开发入门到精通基础教程',
  //     storeId: '1543543',
  //     placeOfShip: '10001',
  //     brand: '人民邮电出版社',
  //     imgUrl:
  //       'https://picasso.alicdn.com/imgextra/O1CNA1Y7QTGp24bNCw8A69o_!!2145487409-0-psf.jpg_430x430q90.jpg',
  //     comment: [],
  //     price: '45',
  //   },
  //   {
  //     id: 'xacargvgf2ioma',
  //     name: '《算法第四版》',
  //     description:
  //       '算法 第4版 图解算法入门经典教程书籍 Java语言实现50种算法数据结构与算法分析 程序员进阶算法导论计算机编程基础算法第四版',
  //     storeId: '1543543',
  //     placeOfShip: '10001',
  //     brand: '人民邮电出版社',
  //     imgUrl:
  //       'https://img.alicdn.com/imgextra/i4/366639179/O1CN01FUgxYu2Hg2EKJFCre_!!0-item_pic.jpg_430x430q90.jpg',
  //     comment: [],
  //     price: '68',
  //   },
  // ];

  const [goods, setGoods] = useState<API.Goods[] | undefined>(undefined);

  useEffect(() => {
    const getAllUser = async () => {
      const res = await queryGoods();
      console.log(res);
      setGoods(res.data);
    };
    getAllUser();
  }, []);

  const [visibleTwo, setVisibleTwo] = useState(false);
  const [currentGood, setCurrentGood] = useState<API.Goods | undefined>(undefined);
  const [detailGoodEdit, setDetailGoodEdit] = useState<boolean>(false);
  const showDrawerTwo = () => {
    setVisibleTwo(!visibleTwo);
  };

  const onClose = () => {
    setVisibleTwo(false);
    setDetailGoodEdit(false);
  };

  const info = (msg: string) => {
    message.info(msg);
  };

  const [isEditor, setIsEditor] = useState<boolean>(false);
  const onFinish = async (values) => {};

  const editGoodInfo = () => {
    setDetailGoodEdit(!detailGoodEdit);
  };

  return (
    <>
      {isEditor ? (
        <div>
          <h1>添加商品</h1>
          <div className={styles.form}>
            <Form
              name="basic"
              labelCol={{ span: 4 }}
              wrapperCol={{ span: 16 }}
              onFinish={onFinish}
              autoComplete="off"
              size="large"
              className={styles.formInside}
            >
              <Form.Item
                label="商品名称"
                name="name"
                rules={[{ required: true, message: '请输入商品名称！' }]}
              >
                <Input />
              </Form.Item>

              <Form.Item
                label="商品描述"
                name="description"
                rules={[{ required: true, message: '请输入商品描述' }]}
              >
                <Input.TextArea />
              </Form.Item>

              <Form.Item
                label="商品价格"
                name="price"
                rules={[{ required: true, message: '请输入商品价格' }]}
              >
                <Input />
              </Form.Item>

              <Form.Item
                label="发货地址"
                name="placeOfShipment"
                rules={[{ required: true, message: '请输入商品发货地' }]}
              >
                <Input />
              </Form.Item>

              <Form.Item
                label="商品品牌"
                name="brand"
                rules={[{ required: true, message: '请输入商品品牌' }]}
              >
                <Input />
              </Form.Item>

              <Form.Item
                label="商品图片"
                name="imgUrl"
                rules={[{ required: true, message: '请输入商品图片' }]}
              >
                <Input />
              </Form.Item>

              <Form.Item wrapperCol={{ offset: 4, span: 16 }}>
                <Button type="primary" htmlType="submit">
                  提交
                </Button>
              </Form.Item>
            </Form>
          </div>
        </div>
      ) : (
        <div>
          <h1>店铺商品</h1>
          <div className={styles.container}>
            {goods &&
              goods.map((el) => {
                return (
                  <Card
                    key={el.id}
                    hoverable
                    style={{ width: 240, margin: 20 }}
                    cover={<img alt="example" src={el.imgUrl} />}
                    onClick={() => {
                      showDrawerTwo();
                      setCurrentGood(el);
                      return undefined;
                    }}
                    activeTabKey={el.id}
                  >
                    <span className={styles['g-name']}>{el.name}</span>
                    <span>￥{el.price.toFixed(2)}</span>
                    <div>{el.brand}</div>
                  </Card>
                );
              })}

            <Drawer
              title="商品详情"
              placement="right"
              closable={false}
              onClose={onClose}
              size="large"
              visible={visibleTwo}
              key="right"
            >
              <div className={styles.detailImg}>
                <Image src={currentGood?.imgUrl} className={styles.detailImg} width={400} />
              </div>
              <h1 className={styles.detailName}>
                <Badge.Ribbon text="认证">
                  <Card title="店铺信息" size="small">
                    {currentGood?.brand}
                  </Card>
                </Badge.Ribbon>
                <div className={styles.detailNName}>{currentGood?.name}</div>
              </h1>
              <div className={styles.detailPrice}>￥{currentGood?.price.toFixed(2)}</div>
              <div className={styles.detailInfo}>{currentGood?.description}</div>
              <Button
                type="primary"
                size="large"
                shape="round"
                className={styles.add}
                onClick={editGoodInfo}
              >
                修改商品信息
              </Button>
              {detailGoodEdit && (
                <Form
                  name="basic"
                  labelCol={{ span: 4 }}
                  wrapperCol={{ span: 16 }}
                  onFinish={onFinish}
                  autoComplete="off"
                >
                  <Form.Item
                    label="商品名称"
                    name="name"
                    initialValue={currentGood?.name}
                    rules={[{ required: true, message: '请输入商品名称！' }]}
                  >
                    <Input />
                  </Form.Item>

                  <Form.Item
                    label="价格"
                    name="price"
                    initialValue={currentGood?.price}
                    rules={[{ required: true, message: '请输入价格' }]}
                  >
                    <Input />
                  </Form.Item>

                  <Form.Item
                    label="商品描述"
                    name="description"
                    initialValue={currentGood?.description}
                    rules={[{ required: true, message: '请输入商品描述' }]}
                  >
                    <Input.TextArea />
                  </Form.Item>

                  <Form.Item wrapperCol={{ offset: 4, span: 16 }}>
                    <Button type="primary" htmlType="submit">
                      提交
                    </Button>
                  </Form.Item>
                </Form>
              )}
            </Drawer>
          </div>
        </div>
      )}
      <Button
        type="primary"
        shape="round"
        icon={<PlusOutlined />}
        size="large"
        className={styles.floatBtnTwo}
        onClick={() => setIsEditor(!isEditor)}
      >
        添加商品
      </Button>
    </>
  );
};

export default Store;
