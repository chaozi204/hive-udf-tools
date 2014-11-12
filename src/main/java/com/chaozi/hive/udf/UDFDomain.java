package com.chaozi.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.udf.UDFType;

@Description(name = "domain", value = "_FUNC_(url) - Returns a generated url type")
@UDFType(deterministic = false)
public class UDFDomain extends UDF {

	public String evaluate(String url) {
		if (url == null || url.length() < 8) {
			return "";
		} else {
			String[] hosts = url.split("/");
			if (hosts.length < 3) {
				return "";
			} else {
				String domain=hosts[2].toLowerCase();
				if(domain.indexOf(".")<0){
					return "";
				}else if(domain.indexOf(":")>0){
					String tmp=domain.split(":")[0];
					if(tmp.length()<5){
						return "";
					}
					return tmp;
				}else{
					if(domain.length()<5){
						return "";
					}
					return domain;
				}
			
			}
		}
	}

}
