import com.ssh.entity.Emp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test {
    @org.junit.Test
    public void test() {
        //1.获取SessionFactory 会话工厂
        SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
        //2.生产会话 通过opensession 获取session对象
        Session session =sessionFactory.openSession();
        //3.开启一个事务
        Transaction transaction = session.beginTransaction();

        //4.1执行条件查询操作 (要查询的实体类的类型,参数) load直接加载
        Emp emp1 = session.load(Emp.class,3);
        System.out.println("load1"+emp1);
       /*transaction.commit();//提交事务
        session.close();//关闭session
        System.out.println("*****************************************************************");
        session=sessionFactory.openSession();
        Transaction transaction1 = session.beginTransaction();*/
        //4.2执行条件查询操作 get 懒加载
        Emp emp = session.get(Emp.class,3);
        System.out.println("load2"+emp);
        //4.3执行条件查询操作 hql语句
        String hql1="from Emp where id=:id";
        Query query = session.createQuery(hql1);
        query.setParameter("id",22);//替换占位符
        Object o = query.uniqueResult();//只针对于已经知道只有一行结果时才能使用
        System.out.println(o);
        //5.查询全部
        Query query1 = session.createQuery("from com.ssh.entity.Emp");
        List<Emp> list = query1.list();
        for(Emp emp2:list){
            System.out.println(emp2);
        }
    }
}
