package aurionpro.erp.ipms.ticketmgmt.trip;

import java.util.List;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

	public class TripRecordDto extends JKDEntityAuditWithId  {

		private int tripId;
		
		private int parentId;
		
		private int groupId;
		
		private Double latitude;
		
		private Double longitude;
		
		private Long startTime;
		
		private Long endTime;
		
		private Float maxSpeed;
		
		private String urlString;

		private String polyline;
		
		private Double sourceLat;
		
		private Double sourceLong;
		
		private Double destinationLat;
		
		private Double destinationLong;
		
		private Float distanceValue;
		
		private String speed;
		
		private String batteryLevel;
		
		private String distance;
		
		private Boolean isDriver;
		
		private List<String> ewayInfoList;
		
		private List<String> emailList;
		
		private String deliveryTime;
		
		private Boolean isOutOfPeriod;
		
		private Boolean isEwayPackage;
		
		private String locationType;
		
		private String ignitionEvent;
		
		private String obdSerialId;
		
		private String sourceAdd;
		
		private String destAdd;

		public int getTripId() {
			return tripId;
		}

		public void setTripId(int tripId) {
			this.tripId = tripId;
		}

		public int getParentId() {
			return parentId;
		}

		public void setParentId(int parentId) {
			this.parentId = parentId;
		}

		public int getGroupId() {
			return groupId;
		}

		public void setGroupId(int groupId) {
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

		public Float getDistanceValue() {
			return distanceValue;
		}

		public void setDistanceValue(Float distanceValue) {
			this.distanceValue = distanceValue;
		}

		public String getSpeed() {
			return speed;
		}

		public void setSpeed(String speed) {
			this.speed = speed;
		}

		public String getBatteryLevel() {
			return batteryLevel;
		}

		public void setBatteryLevel(String batteryLevel) {
			this.batteryLevel = batteryLevel;
		}

		public String getDistance() {
			return distance;
		}

		public void setDistance(String distance) {
			this.distance = distance;
		}

		public Boolean getIsDriver() {
			return isDriver;
		}

		public void setIsDriver(Boolean isDriver) {
			this.isDriver = isDriver;
		}

		public List<String> getEwayInfoList() {
			return ewayInfoList;
		}

		public void setEwayInfoList(List<String> ewayInfoList) {
			this.ewayInfoList = ewayInfoList;
		}

		public List<String> getEmailList() {
			return emailList;
		}

		public void setEmailList(List<String> emailList) {
			this.emailList = emailList;
		}

		public String getDeliveryTime() {
			return deliveryTime;
		}

		public void setDeliveryTime(String deliveryTime) {
			this.deliveryTime = deliveryTime;
		}

		public Boolean getIsOutOfPeriod() {
			return isOutOfPeriod;
		}

		public void setIsOutOfPeriod(Boolean isOutOfPeriod) {
			this.isOutOfPeriod = isOutOfPeriod;
		}

		public Boolean getIsEwayPackage() {
			return isEwayPackage;
		}

		public void setIsEwayPackage(Boolean isEwayPackage) {
			this.isEwayPackage = isEwayPackage;
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

		public String getObdSerialId() {
			return obdSerialId;
		}

		public void setObdSerialId(String obdSerialId) {
			this.obdSerialId = obdSerialId;
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

				
		
	}



