import React, { useEffect, useState } from 'react';
import { Card, Drawer, Button, Image, Badge, message } from 'antd';
import { ShoppingCartOutlined, PlusCircleOutlined } from '@ant-design/icons';
import styles from './index.less';
import { queryGoods } from '@/services/request/api';

const Recommend: React.FC = () => {
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

  const [visible, setVisible] = useState(false);

  const showDrawer = () => {
    setVisible(!visible);
  };

  const [visibleTwo, setVisibleTwo] = useState(false);
  const [currentGood, setCurrentGood] = useState<API.Goods | undefined>(undefined);
  const [trolley, setTrolley] = useState<API.Goods[] | []>([]);
  const showDrawerTwo = () => {
    setVisibleTwo(!visibleTwo);
  };

  const onClose = () => {
    setVisible(false);
    setVisibleTwo(false);
  };

  const info = (msg: string) => {
    message.info(msg);
  };

  const addGoodToTrolley = () => {
    // const newA = [...trolley, good as API.Goods];
    console.log(currentGood);
    const newArr = [];
    for (let i = 0; i < trolley.length; i++) {
      newArr.push(trolley[i]);
    }
    newArr.push(currentGood);
    setTrolley(newArr as API.Goods[]);
    info('添加成功！');
  };

  const computedTotal = () => {
    let sum = 0;
    if (trolley.length != 0) {
      for (let i = 0; i < trolley.length; i++) {
        sum += trolley[i].price;
      }
    }
    return sum;
  };

  return (
    <>
      <h1>推荐商品</h1>
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
          title="我的购物车"
          placement="bottom"
          closable={false}
          onClose={onClose}
          size="large"
          visible={visible}
          key="bottom"
        >
          <div className={styles.trolley}>
            {trolley.length > 0 &&
              trolley.map((el, index) => {
                return (
                  <Card
                    key={el.id + index.toString()}
                    hoverable
                    style={{ width: 240, margin: 20 }}
                    cover={<img alt="example" src={el.imgUrl} />}
                    onClick={() => {
                      showDrawerTwo();
                      showDrawer();
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
          </div>
          <div className={styles.priceBox}>
            <div className={styles.computedP}>
              总价格:￥
              {computedTotal().toFixed(2)}
            </div>
            <Button shape="round" type="primary" size="large">
              结算
            </Button>
          </div>
        </Drawer>

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
            onClick={addGoodToTrolley}
          >
            加入购物车
          </Button>
        </Drawer>

        <Button
          type="primary"
          shape="round"
          icon={<ShoppingCartOutlined />}
          size="large"
          className={styles.floatBtn}
          onClick={showDrawer}
        >
          购物车
        </Button>
      </div>
    </>
  );
};

export default Recommend;
