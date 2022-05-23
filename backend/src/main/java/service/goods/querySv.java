package service.goods;

import DAO.GoodsImp;
import entity.Goods;

import java.util.HashSet;

public class querySv {
  public static HashSet<Goods> queryGoods(){
    GoodsImp op = new GoodsImp();
    try{
      return op.queryGoods();
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

}
