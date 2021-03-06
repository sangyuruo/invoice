package com.hl.dao;

import java.util.List;
import java.util.Map;
import com.hl.domain.Invoice;

public interface InvoiceDao {

	int addRecognizeInvoice(Map<String, Object> invoice_data,Invoice invoice);
	void deleteAllInvoiceForeginModel();
	void deleteInvoiceForeginModel(int model_id);
	List<Invoice> getTwentyFaultInvoice(Integer page);
}
