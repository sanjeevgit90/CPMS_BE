package aurionpro.erp.ipms.assetmgmt.common;

import java.math.BigInteger;

public interface AssetCountList {

    BigInteger getCount();
    String getParent();
    String getChild();
}