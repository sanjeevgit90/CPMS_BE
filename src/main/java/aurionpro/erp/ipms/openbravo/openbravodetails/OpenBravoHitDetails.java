package aurionpro.erp.ipms.openbravo.openbravodetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "open_bravo_hit_details", schema = "openbravo")
public class OpenBravoHitDetails extends JKDEntityAuditWithId {
	
	@Column(name = "input_data",  columnDefinition = "text")
	private String inputData;
	
	@Column(name = "output_data",  columnDefinition = "text")
	private String outputData;

	public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}

	public String getOutputData() {
		return outputData;
	}

	public void setOutputData(String outputData) {
		this.outputData = outputData;
	}
}