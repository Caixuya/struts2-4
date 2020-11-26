package action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.config.entities.ActionConfig;

import dao.UserDAO;
import po.User;

public class Login {
	private String username;
	private String password;
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//����¼
	public String login() {		
		//��¼ʧ��,��ʾ���û������������
		UserDAO dao=new UserDAO();
		User u=new User(username,password);
		List<User> list = dao.findByExample(u);
		if(list.size()==0) {
			ActionContext.getContext().getValueStack().set("message", "�û������������");
			return "error";
		}
		
		//��¼�ɹ�����ת��manageҳ�棬��ʾ��user������house����Ϣ
		ServletActionContext.getRequest().getSession().setAttribute("currentUser", list.get(0));
		return "ok";		
	}
}
