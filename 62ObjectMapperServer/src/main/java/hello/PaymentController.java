package hello;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

	public List<Payment> lst=new ArrayList<Payment>();
	
	@PostMapping("/pay")
	public PaymentResponse payIns(@RequestBody Payment payment) {
		payment.setPayDate(new Date());
		lst.add(payment);
		PaymentResponse res= new PaymentResponse();
		res.setStatus("success");
		res.setMessage("saved");
		res.setTxDate(new SimpleDateFormat("dd/mm/yyyy HH:mm:ss a").format(new Date()));
		return res;
		
	}
	
	@RequestMapping("/vendor/{vendor}")
	public PaymentResponse getTx(@PathVariable String vendor) {
		PaymentResponse res=new PaymentResponse();
		Payment newp=null;
		for(int i=0;i<lst.size();i++){
			Payment p=lst.get(i);
			if(p.vendor.equals(vendor)){
				newp=p;
			}
		}
		List<Payment> payment=Arrays.asList(newp);
		//List<Payment> payment = Arrays.asList(lst.get(id));
		res.setStatus("success");
		res.setPayments(payment);
		return res;
	}
	

	

}
