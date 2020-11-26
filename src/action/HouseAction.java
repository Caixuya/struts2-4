package action;

import java.sql.Timestamp;//ʱ���
import java.util.Date;//����
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import po.House;
import po.Street;
import po.Type;
import po.User;
import dao.*;

public class HouseAction {
	private String description;
	private Double price;
	private Double street_id;
	private Double type_id;
	private Double floorage;
	private String title;	
	private String contact;
		
	private Double lowPrice;//��ѯ����
	private Double highPrice;
	private Double lowFloorage;
	private Double highFloorage;
							//��ѯ����+user,+title
	
	private Double id;  //����id, ���ڷ�������
	//��ʾ��������
	public String detail() {
		HouseDAO hdao=new HouseDAO();
		House h = hdao.findById(id);
		System.out.println(h.toString());
		ServletActionContext.getRequest().setAttribute("result", h);
		return "ok";
	}

	//��������
	public String search() {
		try {
			//һ�����ղ�ѯ���� title price street_id type_id floorage 
			//��������houseDAO���в�ѯ
			HouseDAO hdao = new HouseDAO();
			
			Street street = new Street();
			if(street_id!=null) {
				street.setId(street_id);
			}else {
				street=null;
			}
			Type type=new Type();
			if(type_id!=null) {
				
				type.setId(type_id);
			}else{
				type = null;
			}
			
			if(title.trim().equals("")) {title=null;}
			System.out.println("���յ�search����"+title+","+lowPrice+","+highPrice+","+lowFloorage+","+highFloorage+","+street_id+","+type_id);

			User user = (User)ServletActionContext.getRequest().getSession().getAttribute("currentUser");

			List<House> list = hdao.findByCriteria(user, title, lowPrice, highPrice, street, type, lowFloorage, highFloorage);
			System.out.println("search����"+list.size()+"��������Ϣ");
			
			ActionContext.getContext().getValueStack().set("houses", list);
			//������manageҳ��ʾ��ѯ���
			
			return "ok";
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("HouseAction.searchʱ��������");
			ActionContext.getContext().getValueStack().set("message","HouseAction.searchʱ��������");
			return "error";
		}
		
	}
		
		//��ʾ�û����������з���
		public String show() {			
			User user=(User)ServletActionContext.getRequest().getSession().getAttribute("currentUser");
			HouseDAO hdao=new HouseDAO();
			List<House> list = hdao.findByCriteria(user,null,null,null,null,null,null,null);
			System.out.println("���ҵ�"+user.getName()+"��������Ϣ"+list.size()+"��");
			ActionContext.getContext().getValueStack().set("houses", list);
			return "ok";
		}
		//��ӷ���
		public String add() {
			//��֤����-��װ��house��-����save�������ڱ���������¼
			//(1) title  contact ����Ϊ��(
			//(2) pirce, street_id,type_id,floorage ����Ϊ��
			if(title.trim().equals("")||contact.trim().equals("")) {
				ActionContext.getContext().getValueStack().set("message", "���ʧ�ܣ�title����ϵ�绰����Ϊ��");
				return "error";
			}						
			if(price==null||street_id==null||type_id==null||floorage==null) {
				ActionContext.getContext().getValueStack().set("message", "�۸�����������������ڽֵ�����Ϊ��");
				return "error";
			}
			
			//*��װ����*
			//��ȡ��ǰ�û�					
			House h=new House();
			Timestamp ts=new Timestamp(new Date().getTime());
			User user = (User)ServletActionContext.getRequest().getSession().getAttribute("currentUser");
			Type type=new Type();
			type.setId(type_id);
			Street str=new Street();
			str.setId(street_id);
			
			h.setUser(user);	
			h.setTitle(title);
			h.setType(type);
			h.setStreet(str);
			h.setContact(contact);
			h.setDescription(description);
			h.setFloorage(floorage);
			h.setPrice(price);
			h.setPubdate(ts);	
			
			System.out.println("��������house:"+h.toString());
			//*��house���浽��Ӧ�ı���*
			HouseDAO hdao=new HouseDAO();
			hdao.save(h);
			ActionContext.getContext().getValueStack().set("message", "���ݷ����ɹ�:"+h.toString());
			return "ok";
		}

		public void setStreet_id(Double street_id) {
			this.street_id = street_id;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public void setContact(String contact) {
			this.contact = contact;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public void setType_id(Double type_id) {
			this.type_id = type_id;
		}

		public void setFloorage(Double floorage) {
			this.floorage = floorage;
		}

		public void setLowPrice(Double lowPrice) {
			this.lowPrice = lowPrice;
		}

		public void setHighPrice(Double highPrice) {
			this.highPrice = highPrice;
		}

		public void setLowFloorage(Double lowFloorage) {
			this.lowFloorage = lowFloorage;
		}

		public void setHighFloorage(Double highFloorage) {
			this.highFloorage = highFloorage;
		}

		public void setId(Double id) {
			this.id = id;
		}
	
}
