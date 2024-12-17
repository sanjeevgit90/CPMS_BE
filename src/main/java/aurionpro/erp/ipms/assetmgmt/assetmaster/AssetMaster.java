package aurionpro.erp.ipms.assetmgmt.assetmaster;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="assetmaster", schema = "assetmgmt",uniqueConstraints = @UniqueConstraint(columnNames ={"assettag","assetName","serialno"}))
public class AssetMaster extends AssetAbstractClass{


	
}