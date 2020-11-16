package com.Lomikel.WebService;

import com.Lomikel.HBaser.Schema;
import com.Lomikel.HBaser.BinaryDataRepository;

// Java
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

// Log4J
import org.apache.log4j.Logger;

/** <code>HBase2Table</code> interprets <em>HBase</em> data
  * as a HTML table.
  * @opt attributes
  * @opt operations
  * @opt types
  * @opt visibility
  * @author <a href="mailto:Julius.Hrivnac@cern.ch">J.Hrivnac</a> */
public class HBase2Table {
    
  /** Create. */
  public HBase2Table() {
   reset();
   }
   
  /** Reset all data. */
  public void reset() {
    _repository.clear();
    _schema = null;
    _thead = null;
    _data = null;
    _table.clear();
    _fLengths.clear();
    }
    
  /** Convert <em>HBase</em> table into its Web representation..
    * @param table      The {@link Map} of {@link Map}s as <tt>key-&t;{family:column-&gt;value}</tt>. 
    * @param schema     The {@link Schema} to use (may be <tt>null</tt>).
    * @param repository The {@link BinaryDataRepository} with related binary data. */
  public void processTable(Map<String, Map<String, String>> table,
                           Schema                           schema,
                           BinaryDataRepository             repository) {
    log.info("Processing HBase table");
    if (_table == null) {
      return;
      }
    _table      = table;
    _schema     = schema;
    _repository = repository;
    Set<String> columns0;
    columns0 = new TreeSet<>();
    for (Map<String, String> entry : _table.values()) {
      for (String column : entry.keySet()) {
        columns0.add(column);
        }
      }
    //columns0 = _schema.columnNames();
    // TBD: support non-default columns
    List<String> columns = new ArrayList<>();
    _fLengths.clear();
    for (String family : new String[]{"b", "i", "d", "c", "a", "r"}) {
      _fLengths.put(family, 0);
      for (String column : columns0) {
        if (column.startsWith(family + ":")) {
          _fLengths.put(family, _fLengths.get(family) + 1);
          columns.add(column);
          }
        }
      }
    columns.add("key:time");
    _thead = "";
    String formatter;
    for (String column : columns) {
      formatter = "";
      if (column.startsWith("b:")) {
        formatter += "data-formatter='binaryFormatter' ";
        }
      _thead += "<th data-field='" + column + "' data-sortable='true' data-visible='false' " + formatter + "><b><u>" + column + "</u></b><br/>";
      if (_schema != null) {
        _thead += "<small>" + _schema.type(column) + "</small>";
        }
      //if (!column.startsWith("b:")) {
      //  _thead += histSelector(column);
      //  }
      _thead += "</th>";
      }
    String content;
    String id;
    _data = "";
    boolean firstEntry = true;
    int n = 0;
    for (Map.Entry<String, Map<String, String>> entry : _table.entrySet()) {
      if (n++ > 100) {
        log.warn("BUG: Table is to big, limiting to 100 rows");
        break;
        }
      if (entry.getKey().startsWith("schema")) {
        continue;
        }
      if (!firstEntry) {
        _data += ",";
        }
      else {
        firstEntry = false;
        }
      _data += "{\n";
      _data += "'key':'" + entry.getKey() + "'\n";
      for (String column : columns) {
        content = entry.getValue().get(column);
        _data += ",'" + column + "':'" + entry.getValue().get(column) + "'\n";
        }
      _data += "}\n";
      }
    } 
    
  /** Give the table header.
    * @return The table header. */
  public String thead() {
    return _thead;
    }
    
  /** Give the table content.
    * @return The table content. */
  public String data() {
    return _data;
    }
    
  /** Give the content as a multi{@link Map}.
    * @return The content as a multi{@link Map} <code>key-{column-value,...}</code>. */
  // TBD: _data should be creatd from _table
  public Map<String, Map<String, String>> table() {
    return _table;
    }
     
  /** Give number of columns for a  column family.
    * @param  The column family name.
    * @return The number of columns for a column family*/
  public int familyLength(String family) {
    return _fLengths.get(family);
    }
    
  /** Give the number of known columns.
    * @return The number of known columns. */
  public int width() {
    if (_schema != null) {
      return _schema.size();
      }
    return 0;
    }
    
  /** Give the {@link BinaryDataRepository} with binary content.
    * @return The {@link BinaryDataRepository} with binary content. */
  public BinaryDataRepository repository() {
    return _repository;
    }
    
  /** Group rows according the selected column.
    * @param  idCol The column to use for grouping.
    * @return       The String array (as [ ... ... ]) of row keys to hide.
    *               For each group, corresponding to iDcol, only the last row is kept. */
  public String toHide(String idCol) {
    String id;
    Map<String, TreeSet<String>> id2key = new HashMap<>();
    for (Map.Entry<String, Map<String, String>> entry : _table.entrySet()) {
      if (entry.getKey().startsWith("schema")) {
        continue;
        };
      id = entry.getValue().get(idCol);
      if (!id2key.containsKey(id)) {
        id2key.put(id, new TreeSet<String>());
        }
      id2key.get(id).add(entry.getKey());
      }
    String hidden = "[";
    boolean firstEntry = true;
    for (Map.Entry<String, TreeSet<String>> e : id2key.entrySet()) {
      e.getValue().remove(e.getValue().last());
      for (String s : e.getValue()) {
        if (!firstEntry) {
          hidden += ",";
          }
        else {
          firstEntry = false;
          }
        hidden += "'" + s + "'";
        }
      }
    hidden += "]";
    log.info(idCol + " " + hidden);
    return hidden;
    }
    
  /** Give 2/3D subtable as a JSON array.
    * @param xName The name of the x-axis column.
    * @param yName The name of the y-axis column. 
    * @param zName The name of the z-axis column
    *              (or the blank separated list of them).
    *              Can be <tt>null</tt>.
    * @return  The corresponding data as a JSON array. */
  public String xyz(String xName,
                    String yName,
                    String zName) {
    log.info("Getting data for " + xName + "," + yName + "," + zName);
    String data = "";
    Map<String, String> entry;
    boolean first = true;
    for (Map.Entry<String, Map<String, String>> entry0 : _table.entrySet()) {
      String xVal = null;
      String yVal = null;
      String zVal = null;
      if (!entry0.getKey().startsWith("schema")) {
        if (first) {
          first = false;
          }
        else {
          data += ",";
          }
        entry = entry0.getValue();
        xVal = entry.get(xName);
        yVal = entry.get(yName);
        if (zName != null) {
          int n = 0;
          for (String zN : zName.trim().split(" ")) {
            zVal = entry.get(zN);
            if (n > 0) {
              data +=",";
              }
            data += "{'x':" + xVal + ",'y':" + yVal + ",'z':" + zVal + ",'g':" + n++ + "}";
            }
          }
        else {
          data += "{'x':" + xVal + ",'y':" + yVal + "}";
          }
        }
      }
    data = "[" + data + "]";
    return data; 
    }
    
  /** Give time-dependence as a JSON array.
    * @param yName The name of the y-axis column
    *              (or the blank separated list of them).
    * @return  The corresponding data as a JSON array. */
  public String ty(String yName) {
    log.info("Getting data for " + yName);
    String data = "";
    Map<String, String> entry;
    boolean first = true;
    for (Map.Entry<String, Map<String, String>> entry0 : _table.entrySet()) {
      String tVal = null;
      String yVal = null;
      if (!entry0.getKey().startsWith("schema")) {
        if (first) {
          first = false;
          }
        else {
          data += ",";
          }
        entry = entry0.getValue();
        //tVal = entry.get("key:time");
        tVal = _cProcessor.getTimestamp(entry);
        int n = 0;
        for (String yN : yName.trim().split(" ")) {
          yVal = entry.get(yN);
          if (n > 0) {
            data +=",";
            }
          data += "{'t':" + tVal + ",'y':" + yVal + ",'g':" + n++ + "}";
          }
        }
      }
    data = "[" + data + "]";
    log.info(data);
    return data;
    }
    
  /** Give <em>checkbox</em> for column selection.
    * @param column The column name as <tt>family:column</tt>.
    * @return       The corresponding <em>checkbox</em>. */
  private String histSelector(String column) {
    return "&nbsp;<input type='checkbox' name='y0_" + column + "' class='y' id='y0_" + column + "'></input>";
    }
    
  /** Change used {@link HBaseColumnsProcessor} to a customised one.
    * @param cProcessor The customised {@link HBaseColumnsProcessor}. */
  public static void changeColumnsProcessor(HBaseColumnsProcessor cProcessor) {
    log.info("Changing Columns Processor to " + cProcessor);
    _cProcessor = cProcessor;
    }
    
  private BinaryDataRepository _repository = new BinaryDataRepository();  
  
  private static HBaseColumnsProcessor _cProcessor = new HBaseColumnsProcessor();
    
  private Schema _schema;
  
  private String _thead;
  
  private String _data;
  
  private Map<String, Map<String, String>> _table = new HashMap<>();
  
  private Map<String, Integer> _fLengths = new HashMap<>();

  /** Logging . */
  private static Logger log = Logger.getLogger(HBase2Table.class);

  }
