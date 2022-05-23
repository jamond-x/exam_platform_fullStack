package DAO;

import entity.Goods;

import java.util.HashSet;

public interface GoodsDAO {
  public boolean addGoods(Goods good) throws Exception;
  public boolean deleteGoods(String id) throws Exception;
  public Goods updateGoods(Goods good) throws Exception;
  public HashSet<Goods> queryGoods() throws Exception;
}
