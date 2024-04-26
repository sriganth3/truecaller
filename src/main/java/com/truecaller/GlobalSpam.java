package com.truecaller;

import com.truecaller.common.Constants;

import orestes.bloomfilter.CountingBloomFilter;
import orestes.bloomfilter.FilterBuilder;

public class GlobalSpam {
	
    private CountingBloomFilter<String> globalSpam =  new FilterBuilder(Constants.MAX_GLOBAL_SPAM_COUNT, .01).buildCountingBloomFilter();

    public void reportSpam(String spamNumber, String reportingNumber, String reason) {
    	System.out.println("Spam Number : "+ spamNumber + " reported by " + reportingNumber + " for reason: " + reason);
    	globalSpam.add(spamNumber);
    }
    
    public boolean isGlobalSpam(String number) {
    	return globalSpam.contains(number);
    }
 
}
