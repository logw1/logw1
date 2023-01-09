package model;

import entity.Account;
import entity.Category;
import entity.Dishes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MyDAO extends DBContext {
  public Connection con = null;
  public PreparedStatement ps = null;
  public ResultSet rs = null;
  public String xSql = null;

  public MyDAO() {
     con = connection;
  }
  public void finalize() {
     try {
        if(con != null) con.close();
     }
     catch(Exception e) {
        e.printStackTrace();
     }
  }
  
  public List<Dishes> getAllDish(){//load all sản phẩm trong sql rồi đẩy vào 
      List<Dishes> list =new ArrayList<>();
      String query = "select * from Dishes";
      try{
          con = new DBContext().connection;//mở liên kết với sql
          ps = con.prepareStatement(query);//ném câu lệnh query vào sql
          rs = ps.executeQuery();//chạy câu lệnh
          while(rs.next()){
              list.add(new Dishes(rs.getInt(1),
              rs.getString(2).trim(),
              rs.getString(3).trim(),
              rs.getDouble(4),
              rs.getString(5).trim()
              ));
          }
      }catch(Exception e){
           e.printStackTrace();
      } 
      return list;
  }
  
  public List<Category> getAllCategory(){
      List<Category> list = new ArrayList<>();
      String query= "select * from Category";
      try{
          con = new DBContext().connection;
          ps = con.prepareStatement(query);
          rs = ps.executeQuery();
          while(rs.next()){
              list.add(new Category(rs.getInt(1),
                      rs.getString(2)
              ));
          }
      }catch(Exception e){
          e.printStackTrace();
      }
      return list;
  }
  
  public Dishes getNew(){
      String query = "select top 1 * from Dishes\n"
              + "order by id desc";
      try{
          con = new DBContext().connection;
          ps = con.prepareStatement(query);
          rs = ps.executeQuery();
          while(rs.next()){
              return new Dishes (rs.getInt(1),
              rs.getString(2).trim(),
              rs.getString(3).trim(),
              rs.getDouble(4),
              rs.getString(5).trim());
          }
      }catch(Exception e){
          e.printStackTrace();
      }
      return null;
  }
  
 public List<Dishes> getAllDishByCategory(String cid){//lấy món theo category
      List<Dishes> list =new ArrayList<>();
      String query = "select * from Dishes\n"
              + "where CategoryID=?";
      try{
          con = new DBContext().connection;//mở liên kết với sql
          ps = con.prepareStatement(query);//ném câu lệnh query vào sql
          ps.setString(1, cid);
          rs = ps.executeQuery();//chạy câu lệnh
          while(rs.next()){
              list.add(new Dishes(rs.getInt(1),
              rs.getString(2).trim(),
              rs.getString(3).trim(),
              rs.getDouble(4),
              rs.getString(5).trim()
              ));
          }
      }catch(Exception e){
           e.printStackTrace();
      } 
      return list;
    }
  
 public Dishes getDishDetail(String id){
     String query = "select * from Dishes\n"
             + "where ID=?";
     try{
          con = new DBContext().connection;
          ps = con.prepareStatement(query);
          ps.setString(1, id);
          rs = ps.executeQuery();
          while(rs.next()){
              return new Dishes (rs.getInt(1),
              rs.getString(2).trim(),
              rs.getString(3).trim(),
              rs.getDouble(4),
              rs.getString(5).trim());
          }
      }catch(Exception e){
          e.printStackTrace();
      }
      return null;
    }
 
 public List<Dishes> searchByName(String txtSearch){
     List<Dishes> list = new ArrayList<>();
     String query = "select * from Dishes\n"
             + "where [Name] like ?";
     try{
          con = new DBContext().connection;
          ps = con.prepareStatement(query);
          ps.setString(1,"%"+ txtSearch +"%");
          rs = ps.executeQuery();
          while(rs.next()){
              list.add(new Dishes(rs.getInt(1),
              rs.getString(2).trim(),
              rs.getString(3).trim(),
              rs.getDouble(4),
              rs.getString(5).trim()
              ));
          }
         
     }catch(Exception e){
         e.printStackTrace();
    }
     return list;
 }
 
 public Account login(String user,String pass){
     String query ="select * from Account\n"
             + "where [user]=? and pass=?";
     try{
          con = new DBContext().connection;
          ps = con.prepareStatement(query);
          ps.setString(1,user);
          ps.setString(2,pass);
          rs = ps.executeQuery();
          while(rs.next()){
              return new Account(rs.getInt(1), 
              rs.getString(2), 
              rs.getString(3), 
              rs.getInt(4), 
              rs.getInt(5));
          }
     }catch(Exception e){
         e.printStackTrace();
     }
     return null;
}
 
public Account checkAccountExist(String user){//truyền vào user xem user đã tồn tại chưa
    //nếu hàm checkAccoountExist đã tồn tại user thì trả về 1 object
    //nếu chưa có thì trả về null
     String query ="select * from Account\n"
             + "where [user]=?";
     try{
          con = new DBContext().connection;
          ps = con.prepareStatement(query);
          ps.setString(1,user);
          rs = ps.executeQuery();
          while(rs.next()){
              return new Account(rs.getInt(1), 
              rs.getString(2), 
              rs.getString(3), 
              rs.getInt(4), 
              rs.getInt(5));
          }
     }catch(Exception e){
         e.printStackTrace();
     }
     return null;   
 }

public void signup(String user,String pass){
    String query="insert into Account\n"
            + "values(?,?,0,0)";
    try{
          con = new DBContext().connection;
          ps = con.prepareStatement(query);
          ps.setString(1,user);//đẩy user vào dấu ? thứ nhất
          ps.setString(2,pass);//đẩy pass vào dấu ? thứ hai
          ps.executeUpdate();
    }catch(Exception e){
        e.printStackTrace();
    }
}
 
    public static void main(String[] args) {
        MyDAO DAO = new MyDAO();
        List<Dishes> list= DAO.getAllDish();
        List<Category> listc = DAO.getAllCategory();
        List<Dishes> listd = DAO.getAllDishByCategory("1");
        //System.out.println(DAO.getDishDetail("2"));
        List<Dishes> lists = DAO.searchByName("Coca");
//        for (Dishes s : lists) {
//            System.out.println(s);
//        }
        System.out.println(DAO.login("Adam", "123456"));
    }
}

