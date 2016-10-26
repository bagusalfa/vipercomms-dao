/*
 * GenBPO.java
 *
 * Created on July 8, 2007, 6:06 PM
 */

package viper.comms.dao.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author  alva
 */
public class GenBPO extends javax.swing.JFrame {
    static String packageName;
    /** Creates new form GenBPO */
    public GenBPO() {
        initComponents();
        //jDialog1.setVisible(true);
        //  jOptionPane1.showMessageDialog(this,"pesannya","judul", jOptionPane1.OK_OPTION);
        packageName=jTextPackName.getText();
        
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        //jDirectoryChooser1 = new com.l2fprod.common.swing.JDirectoryChooser();
        jLabel1 = new javax.swing.JLabel();
        jTextDir = new javax.swing.JTextField();
        jBtnOpenDir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextURL = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextClassName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextUserName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextPassword = new javax.swing.JTextField();
        jBtnURL = new javax.swing.JButton();
        jBtnGenerate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextPackName = new javax.swing.JTextField();
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setText("Direktori Tujuan");
        
        jTextDir.setText("c:/");
        
        jBtnOpenDir.setText("Open Direktori");
        jBtnOpenDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOpenDirActionPerformed(evt);
            }
        });
        
        
        jLabel2.setText("URL");
        
        jTextURL.setText("jdbc:firebirdsql:localhost/3050:c:/Siap.fdb");
        
        jLabel3.setText("Class Name");
        
        jTextClassName.setText("org.firebirdsql.jdbc.FBDriver");
        
        jLabel4.setText("User name");
        
        jTextUserName.setText("sysdba");
        
        jLabel5.setText("Password");
        
        jTextPassword.setText("masterkey");
        
        jBtnURL.setText("URL");
        jBtnURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnURLActionPerformed(evt);
            }
        });
        
        
        jBtnGenerate.setText("Generate object");
        jBtnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGenerateActionPerformed(evt);
            }
        });
        
        
        jLabel6.setText("jLabel6");
        
        jTextPackName.setText("siap");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addComponent(jLabel2)
                .addComponent(jLabel3)
                .addComponent(jLabel4)
                .addComponent(jLabel5))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jTextURL, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jTextClassName)
                .addComponent(jTextDir, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jBtnOpenDir)
                .addComponent(jBtnURL)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jTextUserName, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTextPassword, javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(jTextPackName, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addComponent(jBtnGenerate)
                .addComponent(jLabel6))
                .addGap(338, 338, 338))
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jTextDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnOpenDir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(jBtnURL)
                .addComponent(jTextURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(jTextUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(jTextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(jTextPackName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jBtnGenerate)
                .addContainerGap(58, Short.MAX_VALUE))
                );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jBtnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGenerateActionPerformed
// TODO add your handling code here:
        packageName=jTextPackName.getText();
        
        DoGenerate(jTextDir.getText()+"\\",jTextURL.getText(),jTextUserName.getText(),jTextPassword.getText(),jTextClassName.getText());
//        JOptionPane opsi=new JOptionPane();
        JOptionPane.showMessageDialog(this,"Sampun terlaksana , mas");
    }//GEN-LAST:event_jBtnGenerateActionPerformed
    
    private void jBtnURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnURLActionPerformed
// TODO add your handling code here:
        JFileChooser fileChooser =new JFileChooser();
        fileChooser.showOpenDialog(this);
        File myFile=fileChooser.getSelectedFile();
        String strFile=myFile.getAbsolutePath();
        
        jTextURL.setText("jdbc:firebirdsql:localhost/3050:"+strFile);
    }//GEN-LAST:event_jBtnURLActionPerformed
    
    private void jBtnOpenDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOpenDirActionPerformed
// TODO add your handling code here:
        //jDirectoryChooser1.showOpenDialog(this);
        //File myFile=jDirectoryChooser1.getSelectedFile();
        //jTextDir.setText(myFile.getAbsolutePath());
//        jFileChooser1.showOpenDialog(this);
//    File myFile=jFileChooser1.getSelectedFile();
//    jTextField1.setText(myFile.getName());
    }//GEN-LAST:event_jBtnOpenDirActionPerformed
    
    public static void WriteObject(FileWriter out,String[] namaKolom,String[] tipeKolom, String tableName, List primaryKey) throws IOException {
        
        String namaTable;
        namaTable=tableName.toLowerCase();
        namaTable=namaTable.substring(1);
        namaTable=tableName.substring(0,1).toUpperCase()+namaTable;
        out.write("package "+packageName+";\r\n");
        out.write("import java.sql.SQLException;\r\n");
        out.write("import java.util.*;\r\n");
        out.write("import java.util.zip.DataFormatException;\r\n");
        out.write("import viper.comms.dao.conn.ConcreteDataAccessor;\r\n");
        out.write("import viper.comms.dao.conn.Row;\r\n");
        out.write("import viper.comms.dao.conn.IsiBaris;\r\n");
        out.write("import viper.comms.dao.conn.FilterOperation;\r\n");
        out.write("import viper.comms.dao.exception.DataException;\r\n");
        out.write("import viper.comms.dao.conn.SignOn;\r\n");
        out.write("import viper.comms.dao.conn.DataAccessor;\r\n");
        out.write("import java.io.Serializable;\r\n");
        out.write("import viper.comms.dao.conn.IDBObject;\r\n");
        out.write("import viper.comms.dao.conn.IDBObjectExt;\r\n");
        
        out.write("@SuppressWarnings(\"serial\")\r\n");
        out.write("public class "+ToSentenceCase(tableName)+" implements IDBObject,IDBObjectExt,Serializable   {\r\n");
        
        out.write("         ConcreteDataAccessor dataAccessor=null;\r\n");
        out.write("	        Row baris=null;\r\n");
        out.write("	        Row updateRow=null;\r\n");
        out.write("	        FilterOperation operMode=FilterOperation.UNKNOWN;\r\n");
        out.write("	        boolean setModeUpdate=false;\r\n");
        out.write("         List<Row> resultQuery=null;\r\n");
        out.write("	        List<Row> resetRow=null;\r\n");
        out.write("         Iterator i=null;\r\n");
        out.write("\r\n");
        out.write("\r\n");
        String[] columnType={"BLOB SUB_TYPE 1","CHAR","VARCHAR","TIMESTAMP","DECIMAL","NUMERIC","DOUBLE PRECISION","FLOAT","SMALLINT","INTEGER","TINYINT","BIGINT","REAL","DOUBLE","BIT","LONGVARCHAR","BINARY","LONGVARBINARY","DATE","TIME"};
        String[] resultColTypeCast={"Object","String","String","java.util.Date","Double","Double","Double","Double","Short","Integer","Byte","Long","Float","Double","Boolean","String","String","String","java.utill.Date","Time"};
        String[] resultColType=    {"Object","String","String","java.util.Date","double","double","double","double","short","int"    ,"byte","Long","float","double","Boolean","String","String","String","java.utill.Date","Time"};
        
        String[] pk=new String[primaryKey.size()];
        String[] pkProperty=new String[primaryKey.size()];
        
        int pos=0;
        for(Iterator i = primaryKey.iterator(); i.hasNext(); ){
            String containCol=(String) i.next();
            pk[pos]=containCol;
            pkProperty[pos]="PKey"+ToSentenceCase(containCol);
            pos++;
            
        }
        for (int i=0;i<namaKolom.length;i++) {
            int intColumnType=0;
            String jenisKolom;
            for (int j=0;j<columnType.length;j++){
                if (tipeKolom[i].equalsIgnoreCase(columnType[j]))
                    intColumnType=j;
            }
            //out.write(namaKolom[i]+","+tipeKolom[i]);
            jenisKolom=resultColType[intColumnType];
            String kolom=namaKolom[i];
            //System.out.println("--> namatable"+namaTable);
            //System.out.println("--> namakolom"+kolom);
            String kolomStc =ToSentenceCase(kolom);
            out.write("         private "+jenisKolom+" "+kolomStc+";\r\n");
            out.write("			public "+jenisKolom+" get"+kolomStc+"() {\r\n");
            out.write("				return "+kolomStc+";\r\n");
            out.write("			}\r\n");
            out.write("			public void set"+kolomStc+"("+jenisKolom+" loc"+kolomStc+") {\r\n");
            out.write(" 		IsiBaris objBaris=new IsiBaris();\r\n");
            out.write("		    objBaris.addParam(operMode); \r\n");
            out.write("			objBaris.addBaris(\""+namaKolom[i]+"\",loc"+kolomStc+");\r\n");
            
            out.write("			if (!setModeUpdate){\r\n");
            out.write("             if (baris==null){\r\n");
            out.write("	            baris=new Row();\r\n");
            out.write("             }\r\n");
            out.write("			baris.addColumn(\""+namaKolom[i]+"\",objBaris);\r\n");
            
            out.write("			}	else{\r\n");
            out.write("             if (updateRow==null){\r\n");
            out.write("	            updateRow=new Row();\r\n");
            out.write("             }\r\n");
            out.write("			updateRow.addColumn(\""+namaKolom[i]+"\",objBaris);\r\n");
            out.write("			}\r\n");
            out.write("			\r\n");
            out.write("			\r\n");
            
            
            
            out.write("				"+kolomStc+" = loc"+kolomStc+";\r\n");
            
            out.write("			}\r\n");
            
            if (IsPrimaryKey(kolom, pk)){
                
                kolom="PKey"+ToSentenceCase(kolom);
                out.write("         private "+jenisKolom+" "+kolom+";\r\n");
                out.write("			public "+jenisKolom+" get"+kolom+"() {\r\n");
                out.write("				return "+kolom+";\r\n");
                out.write("			}\r\n");
                out.write("			public void set"+kolom+"("+jenisKolom+" loc"+kolom+") {\r\n");
                out.write(" 		IsiBaris objBaris=new IsiBaris();\r\n");
                out.write("		    objBaris.addParam(operMode); \r\n");
                out.write("			objBaris.addBaris(\""+namaKolom[i]+"\",loc"+kolom+");\r\n");
                
                out.write("			if (!setModeUpdate){\r\n");
                out.write("             if (baris==null){\r\n");
                out.write("	            baris=new Row();\r\n");
                out.write("             }\r\n");
                
                out.write("			baris.addColumn(\""+namaKolom[i]+"\",objBaris);\r\n");
                
                out.write("			}	else{\r\n");
                out.write("             if (updateRow==null){\r\n");
                out.write("	            updateRow=new Row();\r\n");
                out.write("             }\r\n");
                
                out.write("			updateRow.addColumn(\""+namaKolom[i]+"\",objBaris);\r\n");
                out.write("			}\r\n");
                out.write("			\r\n");
                out.write("			\r\n");
                
                out.write("				"+kolom+" = loc"+kolom+";\r\n");
                out.write("			}\r\n");
                
            }
                        /*else{
                                kolom=ToSentenceCase(kolom);
                        }*/
        }
        out.write("			public void setOperationMode(FilterOperation operationMode){\r\n");
        out.write("				operMode=operationMode;\r\n");
        out.write("			}\r\n");
        //		delete
        out.write("public boolean delete() throws DataFormatException{\r\n");
                /*out.write("			Row baris=new  Row();\r\n");
                for (int j=0;j<namaKolom.length;j++){
                        String strKolom=namaKolom[j];
                 
                        if (IsPrimaryKey(strKolom, pk)){
                                strKolom="PKey"+ToSentenceCase(strKolom);
                        }else{
                                strKolom=ToSentenceCase(strKolom);
                        }
                        out.write("			baris.addColumn(\""+namaKolom[j]+"\",get"+strKolom+"());\r\n");
                }*/
        out.write("			return dataAccessor.delete(\""+namaTable+"\", updateRow, FilterOperation.AND_TYPE);\r\n");
        out.write("			}\r\n");
        
        //		insert
        out.write("public boolean insert() throws DataFormatException {\r\n");
        out.write("       List<Row> untukInsert =  new LinkedList<Row>();;\r\n");
        
                /*out.write("			Row baris=new  Row();\r\n");
                for (int j=0;j<namaKolom.length;j++){
                        String strKolom=namaKolom[j];
                 
                        if (IsPrimaryKey(strKolom, pk)){
                                strKolom="PKey"+ToSentenceCase(strKolom);
                        }else{
                                strKolom=ToSentenceCase(strKolom);
                        }
                        out.write("			baris.addColumn(\""+namaKolom[j]+"\",get"+strKolom+"());\r\n");
                }*/
        out.write("       untukInsert.add(baris);\r\n");
        out.write("		  return dataAccessor.insert(\""+namaTable+"\", untukInsert);\r\n");
        out.write("			}\r\n");
        //update
        out.write("public boolean update() throws DataFormatException{			\r\n");
                /*out.write("			Row baris=new  Row();\r\n");
                for (int j=0;j<namaKolom.length;j++){
                        String strKolom=namaKolom[j];
                 
                        if (IsPrimaryKey(strKolom, pk)){
                                strKolom="PKey"+ToSentenceCase(strKolom);
                        }else{
                                strKolom=ToSentenceCase(strKolom);
                        }
                        out.write("			baris.addColumn(\""+namaKolom[j]+"\",get"+strKolom+"());\r\n");
                }*/
        //out.write("			Row updateRow=new  Row();\r\n");
                /*for (int j = 0;j<pkProperty.length;j++){
                        out.write("			updateRow.addColumn(\""+pk[j]+"\",get"+pkProperty[j]+"());\r\n");
                }*/
        out.write("			return dataAccessor.update(\""+namaTable+"\",baris, updateRow, FilterOperation.AND_TYPE);\r\n");
        out.write("} \r\n");
        //select
        out.write("public boolean load() throws DataFormatException{			\r\n");
        out.write("			List countryRows\r\n");
        out.write("			= dataAccessor.read(\""+namaTable+"\",null,baris,null, FilterOperation.AND_TYPE);\r\n");
        out.write("			for(Iterator i = countryRows.iterator(); i.hasNext(); ) {\r\n");
        out.write("			Row countryRow = (Row)i.next();\r\n");
        String field;
        for (int i=0;i<namaKolom.length;i++) {
            int intColumnType=0;
            String jenisKolom;
            for (int j=0;j<columnType.length;j++){
                if (tipeKolom[i].equalsIgnoreCase(columnType[j]))
                    intColumnType=j;
            }
            //out.write(namaKolom[i]+","+tipeKolom[i]);
            jenisKolom=resultColTypeCast[intColumnType];
            String kolom=namaKolom[i];
            //System.out.println("--> namatable"+namaTable);
            //System.out.println("--> namakolom"+kolom);
            
            if (IsPrimaryKey(kolom, pk)){
                kolom="PKey"+ToSentenceCase(kolom);
            }else{
                kolom=ToSentenceCase(kolom);
            }
            
            field="countryRow.getColumnValue(\""+namaKolom[i]+"\")\r\n";
            out.write("			if (!("+field+").equals(null)){\r\n");
            out.write("			set"+kolom+"(("+jenisKolom+")"+field+");\r\n");
            out.write("			}\r\n");
        }
        out.write("			}\r\n");
        out.write("			return true;\r\n");
        out.write("			}\r\n");
        //ended
        
        out.write("		public List loadRows() throws DataFormatException{\r\n");
        out.write("			List<Row> daftarObject=new ArrayList<Row>();\r\n");
        out.write("			List countryRows\r\n");
        out.write("			= dataAccessor.read(\""+namaTable+"\",null,baris,null, FilterOperation.AND_TYPE);\r\n");
        out.write("			boolean firstItem =true;\r\n");
        out.write("			for(Iterator i = countryRows.iterator(); i.hasNext(); ) {\r\n");
        out.write("				Row countryRow = (Row)i.next();\r\n");
        out.write("				if (firstItem){\r\n");
        for (int i=0;i<namaKolom.length;i++) {
            int intColumnType=0;
            String jenisKolom;
            for (int j=0;j<columnType.length;j++){
                if (tipeKolom[i].equalsIgnoreCase(columnType[j]))
                    intColumnType=j;
            }
            //out.write(namaKolom[i]+","+tipeKolom[i]);
            jenisKolom=resultColTypeCast[intColumnType];
            String kolom=namaKolom[i];
            //System.out.println("--> namatable"+namaTable);
            //System.out.println("--> namakolom"+kolom);
            
            if (IsPrimaryKey(kolom, pk)){
                kolom="PKey"+ToSentenceCase(kolom);
            }else{
                kolom=ToSentenceCase(kolom);
            }
            field="countryRow.getColumnValue(\""+namaKolom[i]+"\")\r\n";
            out.write("			if (!("+field+").equals(null)){\r\n");
            out.write("			set"+kolom+"(("+jenisKolom+")"+field+");\r\n");
            out.write("			}\r\n");
        }
        out.write("					firstItem=false;\r\n");
        out.write("				}\r\n");
        out.write("				daftarObject.add(countryRow);\r\n");
        out.write("			}\r\n");
        out.write("			resultQuery=daftarObject;\r\n");
        out.write("			resetRow=daftarObject;\r\n");
        out.write("			i=resultQuery.iterator();\r\n");
        out.write("			Next();\r\n");
        out.write("			\r\n");
        
        out.write("			return daftarObject;\r\n");
        
        out.write("		}\r\n");
        
        out.write("		public boolean Next(){\r\n");
        out.write("			if (i.hasNext()){\r\n");
        out.write("				Row countryRow = (Row)i.next();\r\n");
        for (int i=0;i<namaKolom.length;i++) {
            int intColumnType=0;
            String jenisKolom;
            for (int j=0;j<columnType.length;j++){
                if (tipeKolom[i].equalsIgnoreCase(columnType[j]))
                    intColumnType=j;
            }
            //out.write(namaKolom[i]+","+tipeKolom[i]);
            jenisKolom=resultColTypeCast[intColumnType];
            String kolom=namaKolom[i];
            //System.out.println("--> namatable"+namaTable);
            //System.out.println("--> namakolom"+kolom);
            
            if (IsPrimaryKey(kolom, pk)){
                kolom="PKey"+ToSentenceCase(kolom);
            }else{
                kolom=ToSentenceCase(kolom);
            }
            field = "countryRow.getColumnValue(\""+namaKolom[i]+"\")";
            out.write("			if (!("+field+").equals(null)){\r\n");
            out.write("			set"+kolom+"(("+jenisKolom+")"+field+");\r\n");
            out.write("			}\r\n");
        }
        out.write("			return true;\r\n");
    
        out.write("			}else{\r\n");
    out.write("			return false;\r\n");
    
        out.write("		}\r\n");
        
        out.write("		}\r\n");
        
        out.write("		public void ResetRow(){\r\n");
        out.write("		        i=resultQuery.iterator();\r\n");
        out.write("		        Next();\r\n");
        out.write("			resultQuery=resetRow;\r\n");
        out.write("		}\r\n");
        out.write("		public boolean HasNext(){\r\n");
        out.write("			if (i.hasNext()){\r\n");
        out.write("     return true;\r\n");
        out.write("			}else{\r\n");
        out.write("				return false;\r\n");
        out.write("			}\r\n");
        out.write("		}\r\n");
        out.write("		public boolean LoadFromRow(Row myRow){\r\n");
        for (int i=0;i<namaKolom.length;i++) {
            int intColumnType=0;
            String jenisKolom;
            for (int j=0;j<columnType.length;j++){
                if (tipeKolom[i].equalsIgnoreCase(columnType[j]))
                    intColumnType=j;
            }
            //out.write(namaKolom[i]+","+tipeKolom[i]);
            jenisKolom=resultColTypeCast[intColumnType];
            String kolom=namaKolom[i];
            //System.out.println("--> namatable"+namaTable);
            //System.out.println("--> namakolom"+kolom);
            
            if (IsPrimaryKey(kolom, pk)){
                kolom="PKey"+ToSentenceCase(kolom);
            }else{
                kolom=ToSentenceCase(kolom);
            }
            field="myRow.getColumnValue(\""+namaKolom[i]+"\")\r\n";
            out.write("			if (!("+field+").equals(null)){\r\n");
            out.write("			set"+kolom+"(("+jenisKolom+")"+field+");\r\n");
            out.write("			}\r\n");
            //out.write("			set"+kolom+"(("+jenisKolom+")myRow.getColumnValue(\""+namaKolom[i]+"\"));\r\n");
        }
        out.write("			return true;\r\n");
        out.write("		}\r\n");
        
        out.write("			public "+ToSentenceCase(tableName)+"(ConcreteDataAccessor dataAccessor) {\r\n");
        out.write("				super();\r\n");
        out.write("				this.dataAccessor = dataAccessor;\r\n");
        out.write("			}\r\n");
        out.write("			public "+ToSentenceCase(tableName)+"() throws  DataFormatException, DataException{\r\n");
        out.write("				super();\r\n");
        out.write("				this.dataAccessor=new ConcreteDataAccessor();\r\n");
        out.write("			}\r\n");
        
        out.write("			public "+ToSentenceCase(tableName)+"(SignOn loginObject) throws DataException{\r\n");
        out.write("			super();\r\n");
        out.write("			this.dataAccessor=new ConcreteDataAccessor(loginObject);\r\n");
        out.write("			}\r\n");
        out.write("			\r\n");
        
        out.write("			public ConcreteDataAccessor getDataAccessor(){\r\n");
        out.write("				return this.dataAccessor;\r\n");
        out.write("			}\r\n");
        
        out.write("			public boolean ReadInExclusiveParam() throws DataFormatException {\r\n");
        out.write("			List countryRows\r\n");
        out.write("			= dataAccessor.readMOExclusiveParam(\""+namaTable+"\", null, baris, null, FilterOperation.AND_TYPE);\r\n");
        out.write("			for(Iterator i = countryRows.iterator(); i.hasNext(); ) {\r\n");
        out.write("			Row countryRow = (Row)i.next();\r\n");
        
        for (int i=0;i<namaKolom.length;i++) {
            int intColumnType=0;
            String jenisKolom;
            for (int j=0;j<columnType.length;j++){
                if (tipeKolom[i].equalsIgnoreCase(columnType[j]))
                    intColumnType=j;
            }
            //out.write(namaKolom[i]+","+tipeKolom[i]);
            jenisKolom=resultColTypeCast[intColumnType];
            String kolom=namaKolom[i];
            //System.out.println("--> namatable"+namaTable);
            //System.out.println("--> namakolom"+kolom);
            
            if (IsPrimaryKey(kolom, pk)){
                kolom="PKey"+ToSentenceCase(kolom);
            }else{
                kolom=ToSentenceCase(kolom);
            }
            field="countryRow.getColumnValue(\""+namaKolom[i]+"\")";
            out.write("			if (!("+field+").equals(null)){\r\n");
            out.write("			set"+kolom+"(("+jenisKolom+")"+field+");\r\n");
            out.write("			}\r\n");
        }
        out.write("			}\r\n");
        out.write("			return true;\r\n");
        out.write("			}\r\n");
        
        out.write("			public boolean deleteInExclusiveParam() throws DataFormatException{\r\n");
        out.write("				return dataAccessor.deleteInExclusiveParam(\""+namaTable+"\", baris, FilterOperation.AND_TYPE);\r\n");
        out.write("			}\r\n");
        out.write("			public boolean updateInExclusiveParam() throws DataFormatException{\r\n");
        out.write("				return dataAccessor.updateInExclusiveParam(\""+namaTable+"\", baris, updateRow, FilterOperation.AND_TYPE);\r\n");
        out.write("			}\r\n");
        out.write("			public boolean isSetModeUpdate() {\r\n");
        out.write("				return setModeUpdate;\r\n");
        out.write("			}\r\n");
        out.write("			public void setSetModeUpdate(boolean setModeUpdate) {\r\n");
        out.write("				this.setModeUpdate = setModeUpdate;\r\n");
        out.write("			}\r\n");
        out.write("			public void setDataAccessor(ConcreteDataAccessor dataAccessor) {\r\n");
        out.write("				this.dataAccessor = dataAccessor;\r\n");
        out.write("			}\r\n");
        
        out.write("public boolean BeginTransaction() throws SQLException {\r\n");
        out.write("	return this.dataAccessor.beginTransaction();\r\n");
        out.write("}\r\n");
        out.write("public boolean Commit() {\r\n");
        out.write("	try {\r\n");
        out.write("		this.dataAccessor.commit();\r\n");
        out.write("	} catch (SQLException e) {\r\n");
        out.write("		e.printStackTrace();\r\n");
        out.write("		return false;\r\n");
        out.write("	}\r\n");
        out.write("	return true;\r\n");
        out.write("}\r\n");
        
        out.write("public boolean Rollback() {\r\n");
        out.write("	try {\r\n");
        out.write("		this.dataAccessor.rollback();\r\n");
        out.write("	} catch (SQLException e) {\r\n");
        out.write("		e.printStackTrace();\r\n");
        out.write("		return false;\r\n");
        out.write("	}\r\n");
        out.write("	return true;\r\n");
        out.write("}\r\n");
        out.write("public List ExecQuery(String querySQL) {\r\n");
        out.write("	return this.dataAccessor.execQuery(querySQL);\r\n");
        out.write("}\r\n");
        out.write("\r\n");
        out.write("public boolean NonExecQuery(String querySQL) {\r\n");
        out.write("	return this.dataAccessor.execNonQuery(querySQL);\r\n");
        out.write("}\r\n");
        out.write("\r\n");
        out.write("public List readDynamic(String whereClause,String orderByClause) {\r\n");
        out.write("	return this.dataAccessor.readDynamic(\""+namaTable+"\", whereClause, orderByClause);\r\n");
        out.write("}\r\n");
        
        out.write("			}\r\n");
        
        
    }
    public static String ToSentenceCase(String masukan){
        String kalimat = masukan;
        kalimat=kalimat.toLowerCase();
        kalimat=kalimat.substring(1);
        kalimat=masukan.substring(0,1).toUpperCase()+kalimat;
        
        return kalimat;
    }
    
    public static boolean IsPrimaryKey(String namaKolom,String[] pk){
        //System.out.println("masuk ke dalam");
        for (int i = 0;i<pk.length;i++){
            //System.out.println("i: "+i);
            if (pk[i].equals(namaKolom)){
                return true;
            }
        }
        return false;
        
    }
    public void DoGenerate(String path,String databaseURL,String user,String password,String classDriver){
        java.sql.Driver d = null;
        java.sql.Connection c = null;
        java.sql.Statement s = null;
        java.sql.ResultSet rs = null;
        try {
            //d = new org.firebirdsql.jdbc.FBDriver ();
            
            Class.forName(classDriver).newInstance();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        try {
            
            // We pass the entire database URL, but we could just pass "jdbc:interbase:"
            d = java.sql.DriverManager.getDriver(databaseURL);
            c = java.sql.DriverManager.getConnection(databaseURL, user, password);
            
            java.sql.DatabaseMetaData dbMetaData = c.getMetaData();
            java.sql.ResultSet tables = dbMetaData.getTables(null, null, "%", new String[] {"TABLE"});
            while (tables.next()) {
                System.out.println(tables.getString("TABLE_NAME") + " is a table.");
                s = c.createStatement();
                ResultSet prima= dbMetaData.getPrimaryKeys(null,null,"\""+tables.getString("TABLE_NAME")+"\"" );
                List primaryKey= new ArrayList();
                while (prima.next()){
                    //System.out.println("Table name : "+tables.getString ("TABLE_NAME"));
                    //System.out.println("PK name : "+ prima.getString("PK_NAME"));
                    System.out.println("Column Name : "+prima.getString("COLUMN_NAME"));
                    System.out.println("Sequence : "+prima.getString("KEY_SEQ"));
                    primaryKey.add(prima.getString("COLUMN_NAME"));
                }
                File outFile= new File(path+ToSentenceCase(tables.getString("TABLE_NAME"))+".java");
                FileWriter outWriter = null ;
                try {
                    outFile.createNewFile();
                    outWriter = new FileWriter(outFile);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                try {
                    rs = s.executeQuery("select * from \""+tables.getString("TABLE_NAME")+"\"");
                    ResultSetMetaData rsmd=rs.getMetaData();
                    int jmlKolom =rsmd.getColumnCount();
                    String[] namaKolom= new String[jmlKolom];
                    String[] tipeKolom=new String[jmlKolom];
                    
                    for (int i = 1;i<=jmlKolom;i++) {
                        System.out.println(i+"."+ rsmd.getColumnName(i)+","+rsmd.getColumnTypeName(i));
                        //outWriter.write(i+"."+ rsmd.getColumnName(i)+","+rsmd.getColumnTypeName(i)+"\r\n");
                        namaKolom[i-1]=rsmd.getColumnName(i);
                        tipeKolom[i-1]=rsmd.getColumnTypeName(i);
                        
                    }
                    System.out.println("----------------");
                    WriteObject(outWriter,namaKolom,tipeKolom,tables.getString("TABLE_NAME"),primaryKey);
                    outWriter.close();
                    
                } catch (java.sql.SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                rs.close();
            }
            tables.close();
            
//			s = c.createStatement ();
//			rs = s.executeQuery ("select * from employee where salary < 50000");
//			ResultSetMetaData rsmd=rs.getMetaData();
//			int jmlKolom =rsmd.getColumnCount();
//			for (int i = 1;i<=jmlKolom;i++)
//			{
//			System.out.println("Kolom ke"+i);
//			System.out.println("Nama kolom :"+ rsmd.getColumnName(i));
//			System.out.println("Tipe kolom :"+rsmd.getColumnTypeName(i));
//			System.out.println("----------------");
            
//			}
            
            
        } catch (java.sql.SQLException e) {
            System.out.println("Unable to find Firebird JCA-JDBC driver among the registered drivers.");
            e.printStackTrace();
            return;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenBPO().setVisible(true);
            }
        });
    }
    
// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnGenerate;
    private javax.swing.JButton jBtnOpenDir;
    private javax.swing.JButton jBtnURL;
//    private com.l2fprod.common.swing.JDirectoryChooser jDirectoryChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextClassName;
    private javax.swing.JTextField jTextDir;
    private javax.swing.JTextField jTextPackName;
    private javax.swing.JTextField jTextPassword;
    private javax.swing.JTextField jTextURL;
    private javax.swing.JTextField jTextUserName;
// End of variables declaration//GEN-END:variables
    
}
