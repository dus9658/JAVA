package user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class UserMain {
    public static void main(String[] args) {
        UserUI userUI = new UserUI();
        UserDao userDao = new UserDao("./tmp/users.dat");
        List<User> users = userDao.getUsers();

        while (true) {
            int menuId = userUI.menu();
            if(menuId == 5){
                System.out.println("프로그램을 종료합니다.");
                userDao.saveUser(users);
                break;
            }else if(menuId == 1){
                User user = userUI.regUser();
                users.add(user);
                System.out.println("등록되었습니다.");
            }else if (menuId == 2){
                userUI.printUserList(users);
            } else if(menuId == 3){
                String email = userUI.inputEmail();
                int findIndex = -1;
                for(int i = 0; i < users.size(); i++) {
                    User u = users.get(i);
                    if(u.getEmail().equals(email)){
                        findIndex = i;
                        break;
                    }
                }
                if(findIndex != -1) {
                    User updateUser = userUI.inputUser(email);
                    users.remove(findIndex);
                    users.add(updateUser);
                } else {
                    System.out.println("해당 이메일에 맞는 회원이 없습니다.");
                }
            }
            System.out.println();
        }
    }
}
