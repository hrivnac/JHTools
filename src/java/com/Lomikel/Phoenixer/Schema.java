package com.Lomikel.Phoenixer;

import com.Lomikel.DB.CellContent;

// HBase
import org.apache.hadoop.hbase.util.Bytes;

// Java
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

// Log4J
import org.apache.log4j.Logger;

/** <code>Schema</code>handles <em>Phoenix</em> types coding/decoding.
  * @opt attributes
  * @opt operations
  * @opt types
  * @opt visibility
  * @author <a href="mailto:Julius.Hrivnac@cern.ch">J.Hrivnac</a> */
public class Schema {
  
  /** Set overall schema.
    * @param schemaName The name of the schema to set.
    * @param schemaMap  The schema to set.
    * @param rowkeys    The rowkeys to idetify rows. */
  public Schema(String              schemaName,
                Map<String, String> schemaMap,
                String[]    rowkeys) {
    _schemaName = schemaName;
    _schemaMap  = schemaMap;
    _rowkeys    = rowkeys;
    }
    
  /** Decode the column value.
    * @param  column       The column to decode.
    * @param  encodedValue The encoded value.
    * @return              The decoded value.
    *                      Binary values are decoded as <tt>*binary*</tt>,
    *                      or showing their MIME-type, when known.
    *                      Unknown types are decoded as strings. */
  public String decode(String column,
                       String encodedValue) {
    return encodedValue;
    }
    
  /** Decode the column value to {@link CellContent}..
    * @param  column       The column to decode.
    * @param  encodedValue The encoded value.
    * @return              The decoded value.
    *                      Unknown types are decoded as strings. */
  public CellContent decode2Content(String column,
                                    String encodedValue) {
    return new CellContent(encodedValue);
    }
    
  /** Encode the column value. Doesn't encode binary values.
    * @param  column       The column to encode.
    * @param  decodedValue The decoded value.
    * @return              The encoded value.
    *                      Unknown types are decoded as strings. */
  public String encode(String column,
                       String decodedValue) {
    return decodedValue;
    }

  /** Give the column type (from {@link Schema}).
    * @param column The column.
    * @return       The column type. */
  public String type(String column) {
    return _schemaMap.get(column);
    }
    
  /** Give the current number of columns.
    * @return The current number of known columns. */
  public int size() {
    if (_schemaMap != null) {
      return _schemaMap.size();
      }
    return 0;
    }
    
  /** Give all column names.
    * @return The {@link Set} of column names. */
  public Set<String> columnNames() {
    return _schemaMap.keySet();
    }
    
  /** Give schema name.
    * @return The schema name.*/
  public String name() {
    return _schemaName;
    }
    
  /** Get registered {@link Schema}.
    * @param schemaName The name of the requested {@link Schema}.
    * @return The registered {@link Schema}. */
  public static Schema getSchema(String schemaName) {
    return _schemas.get(schemaName);
    }
    
  /** Register named {@link Schema}.
    * @param schemaName The name of the {@link Schema} to register. 
    * @param schemaName The {@link Schema} to register. 
    * @param rowkeys    The rowkeys to idetify rows. */
  public static void addSchema(String              schemaName,
                               Map<String, String> schemaMap,
                               String[]            rowkeys) {
    _schemas.put(schemaName, new Schema(schemaName, schemaMap, rowkeys));
    }
    
  /** Give row identifying key names.
    * @return The row identifying key names. */
 public String[] rowkeyNames() {
   return _rowkeys;
   }
    
  @Override
  public String toString() {
    return "Schema " + name() + " = " + _schemaMap;
    }
    
  private String _schemaName;
    
  private Map<String, String> _schemaMap;
  
  private String[] _rowkeys;
  
  private static Map<String, Schema> _schemas = new HashMap<>();

  /** Logging . */
  private static Logger log = Logger.getLogger(Schema.class);
  
  // TBD:move to Atlascope
  // TBD:make rowkey part of schema also in HBase
  static {
    log.info("Setting know Schemas");
    Map<String, String> schema = new HashMap<>();
    schema.put("runnumber"         , "Integer");
    schema.put("project"           , "String" );
    schema.put("streamname"        , "String" );
    schema.put("prodstep"          , "String" );
    schema.put("datatype"          , "String" );
    schema.put("version"           , "String" );
    schema.put("dspid"             , "Integer"); 
    schema.put("state"             , "String" ); 
    schema.put("state_modification", "Date"   );
    schema.put("state_details"     , "String" );
    schema.put("insert_start"      , "Date"   );
    schema.put("backup_end"        , "Date"   );
    schema.put("backup_start"      , "Date"   );
    schema.put("insert_end"        , "Date"   );
    schema.put("source_path"       , "String" );
    schema.put("validated"         , "Long"   );
    schema.put("count_events"      , "Long"   );
    schema.put("uniq_dupl_events"  , "Long"   );
    schema.put("num_duplicates"    , "Long"   );
    schema.put("trigger_counted"   , "Integer");
    schema.put("ds_overlaps"       , "Integer");
    schema.put("ami_count"         , "Long"   );
    schema.put("ami_raw_count"     , "Long"   );
    schema.put("ami_date"          , "Date"   );
    schema.put("ami_upd_date"      , "Date"   );
    schema.put("ami_state"         , "String" );
    schema.put("inconctainer"      , "Integer");
    schema.put("smk"               , "Integer");
    addSchema("aeidev.datasets", schema, new String[]{"runnumber",
                                                      "project",
                                                      "streamname",
                                                      "prodstep",
                                                      "datatype",
                                                      "version",
                                                      "dspid"});
    schema = new HashMap<>();
    schema.put("runnumber"         , "Integer");
    schema.put("project"           , "String" );
    schema.put("streamname"        , "String" );
    schema.put("prodstep"          , "String" );
    schema.put("datatype"          , "String" );
    schema.put("version"           , "String" );
    schema.put("dspid"             , "Integer"); 
    schema.put("insert_start"      , "Date"   );
    schema.put("insert_end"        , "Date"   );
    schema.put("backup_start"      , "Date"   );
    schema.put("backup_end"        , "Date"   );
    schema.put("validated"         , "Long"   );
    schema.put("count_events"      , "Long"   );
    schema.put("uniq_dupl_events"  , "Long"   );
    schema.put("num_duplicates"    , "Integer");
    schema.put("tigger_counted"    , "Integer");
    schema.put("ds_overlaps"       , "Long"   );
    schema.put("ami_count"         , "Long"   );
    schema.put("ami_raw_count"     , "Long"   );
    schema.put("ami_date"          , "Date"   );
    schema.put("ami_upd_date"      , "String" );
    schema.put("ami_state"         , "Integer");
    schema.put("inconctainer"      , "String" );
    schema.put("state"             , "String" );
    schema.put("smk"               , "Integer");
    addSchema("aei.sdatasets", schema, new String[]{"runnumber",
                                                    "project",
                                                    "streamname",
                                                    "prodstep",
                                                    "datatype",
                                                    "version",
                                                    "dspid"});
    schema =  new HashMap<>();
    schema.put("dspid"             , "Integer");
    schema.put("dstypeid"          , "Short"  );
    schema.put("dssubtypeid"       , "Short"  );
    schema.put("eventno"           , "Long"   );
    schema.put("seq"               , "Short"  );
    schema.put("tid"               , "Integer");
    schema.put("sr"                , "String" ); // binary(24)
    schema.put("pv"                , "String" ); // binary(24) array
    schema.put("lb"                , "Integer");
    schema.put("bcid"              , "Integer");
    schema.put("lpsk"              , "Integer");
    schema.put("etime"             , "Date"   );
    schema.put("id"                , "Long"   );
    schema.put("tbp"               , "Short"  ); // array
    schema.put("tap"               , "Short"  ); // array
    schema.put("tav"               , "Short"  ); // array
    schema.put("lb2"               , "Integer");
    schema.put("bcid2"             , "Integer");
    schema.put("hpsk"              , "Integer");
    schema.put("ph"                , "Short"  ); // array 
    schema.put("pt"                , "Short"  ); // array
    schema.put("rs"                , "Short"  ); // array
    addSchema("aeidev.events", schema, new String[]{"dspid",
                                                    "dstypeid",
                                                    "dssubtypeid",
                                                    "eventno",
                                                    "seq"});
    schema = new HashMap<>();
    schema.put("dspid"             , "Integer");
    schema.put("eventnumber"       , "Long"   );
    schema.put("hltpsk"            , "Integer");
    schema.put("l1psk"             , "Integer");
    schema.put("lumiblocknr"       , "Integer");
    schema.put("bunchid"           , "Integer");
    schema.put("eventtime"         , "Integer");
    schema.put("eventtimens"       , "Integer");
    schema.put("lvl1id"            , "Long"   );
    schema.put("l1trigmask"        , "String" );
    schema.put("l1trigchainstav"   , "String" );
    schema.put("l1trigchainstap"   , "String" );
    schema.put("l1trigchainstbp"   , "String" );
    schema.put("eftrigmask"        , "String" );
    schema.put("eftrigchainsph"    , "String" );
    schema.put("eftrigchainspt"    , "String" );
    schema.put("eftrigchainsrs"    , "String" );
    schema.put("dbraw"             , "String" );
    schema.put("tkraw"             , "String" );
    schema.put("dbesd"             , "String" );
    schema.put("tkesd"             , "String" );
    schema.put("dbaod"             , "String" );
    schema.put("tkaod"             , "String" );
    schema.put("db"                , "String" );
    schema.put("tk"                , "String" );
    addSchema("aei.sevents", schema, new String[]{"dspid",
                                                  "eventnumber"});
    }
  
  }
