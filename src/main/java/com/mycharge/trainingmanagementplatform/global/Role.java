package com.mycharge.trainingmanagementplatform.global;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Role implements ApplicationRunner {

    static public HashMap<String,Integer> role_group=new HashMap<>();//用户组

    //启动时初始化常用用户组 尽量通过role_group设置，方便批量修改
    @Override
    public void run(ApplicationArguments args) throws Exception {
        role_group.put("all",getAll());//直接不检验也可
        role_group.put("all_but_stu",getAll()-role.student.ordinal());
        role_group.put("student",role.student.ordinal());
        role_group.put("teacher",role.teacher.ordinal());
        role_group.put("admin",role.admin.ordinal());
    }


    static public int getRole(String user_type){
        role[] rv=role.values();
        for(role r:rv){
            if(r.toString().equals(user_type)){
                int i=1;
                //2的次方，利用掩码的形式可以组合用户组
                for(int j=0;j<r.ordinal();j++){
                    i*=2;
                }
                return i;
            }
        }
        return -1;
    }

    //所有用户组
    private int getAll(){
        role[] rv=role.values();
        int sum=0;
        for(role r:rv){
            sum+=Math.pow(2,r.ordinal());
        }
        return sum;
    }

    public enum role{
        student, //1
        teacher, //2
        admin    //4
    }
}
