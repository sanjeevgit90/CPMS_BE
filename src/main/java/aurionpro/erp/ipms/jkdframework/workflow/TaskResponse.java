package aurionpro.erp.ipms.jkdframework.workflow;

public class TaskResponse {

    Long taskid;
    String assignToRole;
    String assignToUser;
    String status;
    String action;
    
    public TaskResponse( Long taskid, String assignToRole,String assignToUser, String status, String action ) {
    	this.taskid = taskid;
    	this.assignToRole = assignToRole;
    	this.assignToUser = assignToUser;
    	this.status = status;
    	this.action = action;
    }
    
	public Long getTaskid() {
		return taskid;
	}
	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
	public String getAssignToRole() {
		return assignToRole;
	}
	public void setAssignToRole(String assignToRole) {
		this.assignToRole = assignToRole;
	}
	public String getAssignToUser() {
		return assignToUser;
	}
	public void setAssignToUser(String assignToUser) {
		this.assignToUser = assignToUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
    
    
}