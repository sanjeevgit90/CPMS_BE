package aurionpro.erp.ipms.assetmgmt.locationwiseinstallation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AssetLocationWiseInstallationRepository extends JpaRepository<AssetLocationWiseInstallationMaster,Long>{

	Optional<AssetLocationWiseInstallationMaster> findByLocation(String location);

}