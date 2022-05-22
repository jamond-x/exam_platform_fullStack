import React from 'react';
import { Card } from 'antd';
const { Meta } = Card;
import styles from './index.less';

const Recommend: React.FC = () => {
  const goods = [
    {
      id: 'cesdcdvfvdf',
      name: '《深入浅出Nodejs》',
      description:
        '【官方旗舰店】 深入浅出Node.js 扑灵原创 Node.js开发实战详解计算机编程设计web开发Node.js实战node.js开发入门到精通基础教程',
      storeId: '1543543',
      placeOfShip: '10001',
      brand: '人民邮电出版社',
      imgUrl:
        'https://picasso.alicdn.com/imgextra/O1CNA1Y7QTGp24bNCw8A69o_!!2145487409-0-psf.jpg_430x430q90.jpg',
      comment: [],
      price: '45',
    },
    {
      id: 'xacargvgf2ioma',
      name: '《算法第四版》',
      description:
        '算法 第4版 图解算法入门经典教程书籍 Java语言实现50种算法数据结构与算法分析 程序员进阶算法导论计算机编程基础算法第四版',
      storeId: '1543543',
      placeOfShip: '10001',
      brand: '人民邮电出版社',
      imgUrl:
        'https://img.alicdn.com/imgextra/i4/366639179/O1CN01FUgxYu2Hg2EKJFCre_!!0-item_pic.jpg_430x430q90.jpg',
      comment: [],
      price: '68',
    },
  ];

  return (
    <>
      <div className={styles.container}>
        {goods.map((el) => {
          return (
            <Card
              key={el.id}
              hoverable
              style={{ width: 240, margin: 20 }}
              cover={<img alt="example" src={el.imgUrl} />}
            >
              <span className={styles['g-name']}>{el.name}</span>
              <span>￥{parseFloat(el.price).toFixed(2)}</span>
              <div>{el.brand}</div>
            </Card>
          );
        })}
      </div>
    </>
  );
};

export default Recommend;
