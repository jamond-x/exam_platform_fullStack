package service.goods;

import DAO.GoodsImp;
import entity.Goods;

public class updateSv {
    public static Goods updateGoods(Goods good) throws Exception {
      GoodsImp op = new GoodsImp();
      return op.updateGoods(good);
  }
}
