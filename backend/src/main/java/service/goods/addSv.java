package service.goods;

import DAO.GoodsImp;
import entity.Goods;

public class addSv {
  public static boolean addGood(Goods goods){
    boolean res = false;
    try{
      GoodsImp op = new GoodsImp();
      res = op.addGoods(goods);
      System.out.println(res);
    }catch (Exception e){
      e.printStackTrace();
    }
    if(res){
      return true;
    }else{
      return false;
    }
  }
}
