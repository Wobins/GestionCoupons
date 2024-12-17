package ca.uqac.services;

import java.util.ArrayList;
import java.util.List;

import ca.uqac.coupon.Coupon;


public class DatabaseService {
	private List<Coupon> coupons;
	
	public DatabaseService() {
        this.coupons = new ArrayList<>();
        // Quelques coupons pour tester
        coupons.add(new Coupon("123", "password123"));
        coupons.add(new Coupon("456", "password456"));
    }
	
	public Coupon validateCoupon(String id, String password) {
        for (Coupon coupon : coupons) {
            if (coupon.getId().equals(id) && coupon.getPassword().equals(password)) {
                return coupon;
            }
        }
        return null;
    }
	
	public Coupon findCouponById(String id) {
		Coupon coupon = null;
		for(Coupon c : coupons) {
			if(c.getId() == id) {
				coupon = c;
				break;
			}
		}
		return coupon;
	}
	
	public void deductAmount(Coupon coupon, double amount) {
		coupon.setAmount(coupon.getAmount() - amount);
	}
	
	public double getBalance(String id) {
		Coupon coupon = findCouponById(id);
		return coupon.getAmount();
	}

}
