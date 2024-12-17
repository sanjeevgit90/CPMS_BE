package aurionpro.erp.ipms.ticketmgmt.currency;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface CurrencyRepository  extends JpaRepository<CurrencyMaster,String> {

	@Query("select c.currencyName as selectionid, c.currencyName as selectionvalue from CurrencyMaster c order by c.currencyName")
	List<SelectionList> getAllCurrencyList();

	@Query("select c.currencyName as selectionid, c.currencyName as selectionvalue from CurrencyMaster c where c.isDeleted=false order by c.currencyName")
	List<SelectionList> getActiveCurrencyList();
    
}
