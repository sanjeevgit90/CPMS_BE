package aurionpro.erp.ipms.ticketmgmt.trip;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "tripmaster", schema = "ticketmgmt")
public class TripMaster extends JKDEntityAuditWithId {
	
	private String ticketId;
	
	private String recordId;
	
	private String tripId;
	
	private String parentId;
	
	private String groupId;
	
	private Double latitude;
	
	private Double longitude;
	
	private Long startTime;
	
	private Long endTime;
	
	private Float maxSpeed;
	
	@Column(columnDefinition = "text" )
	private String urlString;

	@Column(columnDefinition = "text" )
	private String polyline;
	
	private Double sourceLat;
	
	private Double sourceLong;
	
	private Double destinationLat;
	
	private Double destinationLong;
	
	private Float distance;
	
	private String locationType;
	
	private String ignitionEvent;
	
	private String vehicleNo;
	
	@Column(length=300)
	private String sourceAdd;
	
	@Column(length=300)
	private String destAdd;
	
	@Transient
	private List<Long> idList;
	
	@Transient
	private List<TripRecordDto> records;
	
	
	
//	@OneToOne(targetEntity=TripRecordDto.class, mappedBy="TripMaster", fetch=FetchType.EAGER)
//	private List<TripRecordDto> records;



	public List<TripRecordDto> getRecords() {
		return records;
	}

	public void setRecords(List<TripRecordDto> records) {
		this.records = records;
	}

	public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	public String getPolyline() {
		return polyline;
	}

	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}

	public Double getSourceLat() {
		return sourceLat;
	}

	public void setSourceLat(Double sourceLat) {
		this.sourceLat = sourceLat;
	}

	public Double getSourceLong() {
		return sourceLong;
	}

	public void setSourceLong(Double sourceLong) {
		this.sourceLong = sourceLong;
	}

	public Double getDestinationLat() {
		return destinationLat;
	}

	public void setDestinationLat(Double destinationLat) {
		this.destinationLat = destinationLat;
	}

	public Double getDestinationLong() {
		return destinationLong;
	}

	public void setDestinationLong(Double destinationLong) {
		this.destinationLong = destinationLong;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getIgnitionEvent() {
		return ignitionEvent;
	}

	public void setIgnitionEvent(String ignitionEvent) {
		this.ignitionEvent = ignitionEvent;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getSourceAdd() {
		return sourceAdd;
	}

	public void setSourceAdd(String sourceAdd) {
		this.sourceAdd = sourceAdd;
	}

	public String getDestAdd() {
		return destAdd;
	}

	public void setDestAdd(String destAdd) {
		this.destAdd = destAdd;
	}

//	public List<TripRecordDto> getRecords() {
//		return records;
//	}
//
//	public void setRecords(List<TripRecordDto> records) {
//		this.records = records;
//	}
	
	

		
	

}