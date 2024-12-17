package aurionpro.erp.ipms.assetmgmt.common;

import java.math.BigInteger;

public interface AssetInstallationData {

    BigInteger getCount();
    String getProduct();
    String getManufacturer();
    String getModel();
    String getSerialno();
    String getLocation();
    String getPolicestation();
    String getStatus();
}