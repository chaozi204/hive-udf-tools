package com.chaozi.hive.hooks;

import org.apache.hadoop.hive.ql.HiveDriverRunHook;
import org.apache.hadoop.hive.ql.HiveDriverRunHookContext;
import org.apache.hadoop.hive.ql.exec.FunctionRegistry;
import org.apache.log4j.Logger;

import com.chaozi.hive.udf.UDFDomain;
import com.chaozi.hive.udtf.ExplodeMap;

/**
 * Use for load user custom udf
 */
public class UDFHooks implements HiveDriverRunHook {

  private static Logger LOG = Logger.getLogger(UDFHooks.class);

  @Override
  public void preDriverRun(HiveDriverRunHookContext hiveDriverRunHookContext) throws Exception {
    LOG.debug("Begin to register custom udf in $HIVE_HOME/auxlib");

    FunctionRegistry.registerUDF(true, "domain", UDFDomain.class,
        false);
    FunctionRegistry.registerGenericUDTF(true, "explode_map", ExplodeMap.class);

    LOG.debug("Finished to register custom udf in $HIVE_HOME/auxlib");
  }

  @Override
  public void postDriverRun(HiveDriverRunHookContext hiveDriverRunHookContext) throws Exception {

  }
}
