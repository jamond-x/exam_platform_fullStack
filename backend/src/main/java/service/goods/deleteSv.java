package service.goods;

import DAO.GoodsImp;

public class deleteSv {
  public static boolean deleteGood(String id) {
    boolean res = false;
    try{
      GoodsImp op = new GoodsImp();
      res = op.deleteGoods(id);
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
