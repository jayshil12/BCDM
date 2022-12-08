package mypackage.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true)
	private Integer id;
	
	@Column(name="firstname", length=40, nullable=false)
	private String firstname;
	
	@Column(name="lastname", length=40, nullable=false)
	private String lastname;
	
	@Column(name="password", length=10, nullable=false)
	private String password;
	
	@Column(name="bronco_id", nullable=false)
	private Integer bronco_id;
	
	@Column(name="phone", nullable=false)
	private String phone;
	
	@Column(name="address", length=100, nullable=false)
	private String address;

	@Column(name="user_type", length=10, nullable=false)
	private String user_type;
	
	@Column(name="department", length=50, nullable=true)
	private String department;
	
	@Column(name="office", length=50, nullable=true)
	private String office;
	
	@Column(name="research", length=50, nullable=true)
	private String research;
	
	@Column(name="major", length=50, nullable=true)
	private String major;
	
	@Column(name="minor", length=50, nullable=true)
	private String minor;
	
	@Column(name="start_date", nullable=true)
	private Date start_date;
	
	@Column(name="grad_date", nullable=true)
	private Date grad_date;
	
	public User() {
		
	}
	
	public User(String firstname, String lastname, String password, Integer bronco_id, String phone, String address, String user_type, String department, String office, String research, String major, String minor, Date start_date, Date grad_date) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.bronco_id = bronco_id;
		this.phone = phone;
		this.address = address;
		this.user_type = user_type;
		this.department = department;
		this.office = office;
		this.research = research;
		this.major = major;
		this.minor = minor;
		this.start_date = start_date;
		this.grad_date = grad_date;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public void setLasttName(String lastname) {
		this.lastname = lastname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getBroncoId() {
		return bronco_id;
	}
	
	public void setBroncoId(Integer bronco_id) {
		this.bronco_id = bronco_id;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getUserType() {
		return user_type;
	}
	
	public void setUserType(String user_type) {
		this.user_type = user_type;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getOffice() {
		return office;
	}
	
	public void setOffice(String office) {
		this.office = office;
	}
	
	public String getResearch() {
		return research;
	}
	
	public void setResearch(String research) {
		this.research = research;
	}
	
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getMinor() {
		return minor;
	}
	
	public void setMinor(String minor) {
		this.minor = minor;
	}
	
	public Date getStartDate() {
		return start_date;
	}
	
	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}
	
	public Date getGradDate() {
		return grad_date;
	}
	
	public void setGradDate(Date grad_date) {
		this.grad_date = grad_date;
	}
}