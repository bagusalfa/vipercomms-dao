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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import tester.Pegawai;
import viper.comms.dao.conn.AbstractEntityManager;
import viper.comms.dao.conn.EntityManager;
import viper.comms.dao.conn.EntityManagerFactory;
import viper.comms.dao.conn.Persistence;
import viper.comms.dao.conn.Row;
import javax.swing.JButton;
import java.awt.Dimension;

/**
 * 
 * @author alva
 */
public class GenTriggerRepl extends javax.swing.JFrame {
	static String packageName;

	/** Creates new form GenBPO */
	public GenTriggerRepl() {
		initComponents();
		// jDialog1.setVisible(true);
		// jOptionPane1.showMessageDialog(this,"pesannya","judul",
		// jOptionPane1.OK_OPTION);
		packageName = jTextPackName.getText();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		jDirectoryChooser1 = new JFileChooser();
		// jDirectoryChooser1 = new
		// com.l2fprod.common.swing.JDirectoryChooser();
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

		jBtnGenerate.setText("Generate replication object");
		jBtnGenerate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBtnGenerateActionPerformed(evt);
			}
		});

		jLabel6.setText("jLabel6");

		jTextPackName.setText("dal");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								jLabel3)
																						.addComponent(
																								jLabel4)
																						.addComponent(
																								jLabel5))
																		.addGap(
																				21,
																				21,
																				21)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addComponent(
																																jTextURL,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																286,
																																Short.MAX_VALUE)
																														.addGroup(
																																layout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				false)
																																		.addComponent(
																																				jTextClassName)
																																		.addComponent(
																																				jTextDir,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				286,
																																				Short.MAX_VALUE)))
																										.addGap(
																												6,
																												6,
																												6)
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jBtnOpenDir)
																														.addComponent(
																																jBtnURL)))
																						.addGroup(
																								layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING,
																												false)
																										.addComponent(
																												jTextUserName,
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(
																												jTextPassword,
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addGroup(
																												layout
																														.createSequentialGroup()
																														.addComponent(
																																jTextPackName,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																277,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
														.addComponent(
																jBtnGenerate)
														.addComponent(jLabel6))
										.addGap(338, 338, 338)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																jTextDir,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jBtnOpenDir,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																23,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(jBtnURL)
														.addComponent(
																jTextURL,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jTextClassName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																jTextUserName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																jTextPassword,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(29, 29, 29)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(
																jTextPackName,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(26, 26, 26).addComponent(
												jBtnGenerate).addContainerGap(
												58, Short.MAX_VALUE)));
		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jBtnGenerateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBtnGenerateActionPerformed
		// TODO add your handling code here:
		packageName = jTextPackName.getText();

		DoGenerate(jTextDir.getText() + "\\", jTextURL.getText(), jTextUserName
				.getText(), jTextPassword.getText(), jTextClassName.getText());
		// JOptionPane opsi=new JOptionPane();
		JOptionPane.showMessageDialog(this, "Sampun terlaksana , mas");
	}// GEN-LAST:event_jBtnGenerateActionPerformed

	private void jBtnURLActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBtnURLActionPerformed
		// TODO add your handling code here:
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(this);
		File myFile = fileChooser.getSelectedFile();
		String strFile = myFile.getAbsolutePath();

		jTextURL.setText("jdbc:firebirdsql:localhost/3050:" + strFile);
	}// GEN-LAST:event_jBtnURLActionPerformed

	private void jBtnOpenDirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBtnOpenDirActionPerformed
		// TODO add your handling code here:
		jDirectoryChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jDirectoryChooser1.showOpenDialog(this);
		File myFile = jDirectoryChooser1.getSelectedFile();
		jTextDir.setText(myFile.getAbsolutePath());
		// jFileChooser1.showOpenDialog(this);
		// File myFile=jFileChooser1.getSelectedFile();
		// jTextField1.setText(myFile.getName());
	}// GEN-LAST:event_jBtnOpenDirActionPerformed



	public static String ToSentenceCase(String masukan) {
		String kalimat = masukan;
		kalimat = kalimat.toLowerCase();
		kalimat = kalimat.substring(1);
		kalimat = masukan.substring(0, 1).toUpperCase() + kalimat;

		return kalimat;
	}

	public static boolean IsPrimaryKey(String namaKolom, String[] pk) {
		// System.out.println("masuk ke dalam");
		for (int i = 0; i < pk.length; i++) {
			// System.out.println("i: "+i);
			if (pk[i].equals(namaKolom)) {
				return true;
			}
		}
		return false;

	}

	public void DoGenerate(String path, String databaseURL, String user,
			String password, String classDriver) {
		java.sql.Driver d = null;
		java.sql.Connection c = null;
		java.sql.Statement s = null;
		java.sql.ResultSet rs = null;
		try {
			// d = new org.firebirdsql.jdbc.FBDriver ();

			Class.forName(classDriver).newInstance();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
		try {

			// We pass the entire database URL, but we could just pass
			// "jdbc:interbase:"
			d = java.sql.DriverManager.getDriver(databaseURL);
			c = java.sql.DriverManager.getConnection(databaseURL, user,
					password);

			java.sql.DatabaseMetaData dbMetaData = c.getMetaData();
			java.sql.ResultSet tables = dbMetaData.getTables(null, null, "%",
					new String[] { "TABLE" });

			File outTriggerFile = new File(path + "ReplicationTrigger.sql");
			FileWriter trig = null;
			
			try {
				outTriggerFile.createNewFile();
				trig = new FileWriter(outTriggerFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while (tables.next()) {
				System.out.println(tables.getString("TABLE_NAME")
						+ " is a table.");
				s = c.createStatement();
				ResultSet prima = dbMetaData.getPrimaryKeys(null, null, "\""
						+ tables.getString("TABLE_NAME") + "\"");
				List primaryKey = new ArrayList();
				while (prima.next()) {
					// System.out.println("Table name : "+tables.getString
					// ("TABLE_NAME"));
					// System.out.println("PK name : "+
					// prima.getString("PK_NAME"));
					System.out.println("Column Name : "
							+ prima.getString("COLUMN_NAME"));
					System.out.println("Sequence : "
							+ prima.getString("KEY_SEQ"));
					primaryKey.add(prima.getString("COLUMN_NAME"));
				}



				try {
					rs = s.executeQuery("select * from \""
							+ tables.getString("TABLE_NAME") + "\"");
					ResultSetMetaData rsmd = rs.getMetaData();
					int jmlKolom = rsmd.getColumnCount();
					String[] namaKolom = new String[jmlKolom];
					String[] tipeKolom = new String[jmlKolom];

					for (int i = 1; i <= jmlKolom; i++) {
						System.out.println(i + "." + rsmd.getColumnName(i)
								+ "," + rsmd.getColumnTypeName(i));
						// outWriter.write(i+"."+
						// rsmd.getColumnName(i)+","+rsmd.getColumnTypeName(i)+"\r\n");
						namaKolom[i - 1] = rsmd.getColumnName(i);
						tipeKolom[i - 1] = rsmd.getColumnTypeName(i);

					}
					System.out.println("----------------");
					if (!(tables
							.getString("TABLE_NAME").toUpperCase().contains("VIPERACTIVITY"))){
					WriteObject(trig, namaKolom, tipeKolom, tables
							.getString("TABLE_NAME"), primaryKey);}
					
//					WriteControllerObject(outWriter, namaKolom, tipeKolom,
//							tables.getString("TABLE_NAME"), primaryKey);
//					WriteEnumObject(outEnumWriter, namaKolom, tables
//							.getString("TABLE_NAME"));
//					outWriter.close();
//					outEnumWriter.close();

				
				} catch (java.sql.SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				rs.close();
			}

			
			
			try {
				trig.write("			CREATE TABLE MYPARAMS (\r\n");
				trig.write("				    TRIGEN  INTEGER\r\n");
				trig.write("				);\r\n");
				
				trig.write("				  \r\n");
				trig.write("			CREATE GENERATOR VIPERACTSEQ;\r\n");
				trig.write("			\r\n");
				trig.write("			CREATE TABLE VIPERACTIVITY (\r\n");
				trig.write("			    ID       NUMERIC(18,0) NOT NULL,\r\n");
				trig.write("			    SQLIPUN  VARCHAR(31999),\r\n");
				trig.write("			    STATUS   VARCHAR(20),\r\n");
				trig.write("			    BRANCH   VARCHAR(20) NOT NULL,\r\n");
				trig.write("			    TANGGAL  DATE DEFAULT CURRENT_TIMESTAMP\r\n");
				trig.write("			);\r\n");
				trig.write("			ALTER TABLE VIPERACTIVITY ADD PRIMARY KEY (ID, BRANCH);\r\n");
				trig.write("				  \r\n");
				trig.write("				  \r\n");
				trig.write("				  \r\n");
				
				trig.write("			CREATE OR ALTER TRIGGER VIPERACTIVITY_BI FOR VIPERACTIVITY\r\n");
				trig.write("			ACTIVE BEFORE INSERT POSITION 0\r\n");
				trig.write("			AS\r\n");
				trig.write("			BEGIN\r\n");
				trig.write("			 new.id= NEXT VALUE FOR viperactseq;\r\n");
				trig.write("			END\r\n");

				trig.write("				  \r\n");
				trig.write("				  \r\n");
			trig.write("\r\n");
			
			trig.write("			CREATE OR ALTER PROCEDURE WRITELOG (\r\n");
			trig.write("				    tablename varchar(50),\r\n");
			trig.write("				    commandtype varchar(30),\r\n");
			trig.write("				    queryvaluelog varchar(32000),\r\n");
			trig.write("				    queryfieldlog varchar(1000))\r\n");
			trig.write("				as\r\n");
			trig.write("				declare variable thisfield varchar(1000);\r\n");
			trig.write("				declare variable thisvalue varchar(32000);\r\n");
			
			trig.write("				declare variable temp varchar(32000);\r\n");
			
			trig.write("				declare variable enabletrig smallint;\r\n");
			trig.write("			begin\r\n");

			trig.write("			\r\n");
			trig.write("			 for select myparams.trigen FROM myparams into :c\r\n");
			trig.write("			 DO\r\n");
			trig.write("			 begin\r\n");
			trig.write("			 END\r\n");
			trig.write("			 \r\n");
			trig.write("			if (:enabletrig >0) then\r\n");
			trig.write("			begin\r\n");
			trig.write("				\r\n");
			trig.write("				  if (:commandtype='INSERT') then\r\n");
			trig.write("				  begin\r\n");
			trig.write("				  temp='INSERT INTO ' || :tablename || '(' || :queryfieldlog || ') values (' || :queryvaluelog || ')';\r\n");
			trig.write("				  end\r\n");
			trig.write("				  else if (:commandtype='DELETE') then\r\n");
			trig.write("				  begin\r\n");
			trig.write("				  thisvalue=:queryvaluelog;\r\n");
			trig.write("				  if (CHAR_LENGTH(:thisvalue)>0) then\r\n");
			trig.write("				begin\r\n");
			trig.write("				thisvalue=substring(:thisvalue FROM 1 FOR CHAR_LENGTH(:thisvalue)-4);\r\n");
			trig.write("				end\r\n");
			trig.write("				  temp='DELETE FROM ' || :tablename || ' where ' || :thisvalue;\r\n");
			trig.write("				  end\r\n");
			trig.write("				  else if (:commandtype='UPDATE') then\r\n");
			trig.write("				  begin\r\n");
			trig.write("				thisvalue=:queryvaluelog;\r\n");
			trig.write("				thisfield=:queryfieldlog;\r\n");
			trig.write("				\r\n");
			trig.write("				if (CHAR_LENGTH(:thisvalue)>0) then\r\n");
			trig.write("				begin\r\n");
			trig.write("				thisvalue=substring(:thisvalue FROM 1 FOR CHAR_LENGTH(:thisvalue)-4);\r\n");
			trig.write("				end\r\n");
			trig.write("				if (CHAR_LENGTH(:thisfield)>0) then\r\n");
			trig.write("				begin\r\n");
			trig.write("				thisfield=substring(:thisfield FROM 1 FOR CHAR_LENGTH(:thisfield)-1);\r\n");
			trig.write("				end\r\n");
			trig.write("				  temp='UPDATE ' || :tablename || ' SET ' || :thisfield || ' WHERE ' || :thisvalue ;\r\n");
			trig.write("				  end\r\n");
			trig.write("				  \r\n");
			trig.write("				  \r\n");
			trig.write("				  \r\n");				  
			trig.write("				  INSERT INTO VIPERACTIVITY ( SQLIPUN, STATUS, BRANCH) VALUES ( :temp, 'PREPARE', 0);\r\n");
			trig.write("				end\r\n");
			trig.write("				end\r\n");
			//==========================================
			trig.write("				  \r\n");
			trig.write("				  \r\n");
			trig.write("				  \r\n");
			trig.write("			CREATE OR ALTER PROCEDURE FLUSHLOG \r\n");
			trig.write("			returns (\r\n");
			trig.write("			    hasil varchar(32000))\r\n");
			trig.write("			as\r\n");
			trig.write("			declare variable chunk varchar(32000);\r\n");
			trig.write("			begin\r\n");
			trig.write("			  for select viperactivity.sqlipun from viperactivity where viperactivity.status='PREPARE'\r\n");
			trig.write("			into :Chunk\r\n");
			trig.write("			 DO\r\n");
			trig.write("			 BEGIN   \r\n");
			trig.write("			  hasil=:Chunk;\r\n");
			trig.write("			  suspend;\r\n");
			trig.write("			       execute statement Chunk;\r\n");
			trig.write("			       \r\n");
			trig.write("			 end\r\n");
			trig.write("			end\r\n");
			trig.write("				  \r\n");
			trig.write("				  \r\n");
			trig.write("				  \r\n");
			

			trig.write("				  \r\n");
			trig.write("				  \r\n");
			trig.write("				  \r\n");
			
			
			
			
				trig.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tables.close();

			

		} catch (java.sql.SQLException e) {
			System.out
					.println("Unable to find Firebird JCA-JDBC driver among the registered drivers.");
			e.printStackTrace();
			return;
		}
	}
	
	public static void WriteObject(FileWriter out, String[] namaKolom,
	String[] tipeKolom, String tableName, List primaryKey)
	throws IOException {

String namaTable;
namaTable = tableName.toLowerCase();
namaTable = namaTable.substring(1);
namaTable = tableName.substring(0, 1).toUpperCase() + namaTable;




out.write("CREATE OR ALTER TRIGGER "
				+ tableName
				+ "_INSREPL FOR "+tableName+" \r\n");

out.write("ACTIVE AFTER INSERT POSITION 100 \r\n");
out.write("AS \r\n");
out.write("declare variable thisvalue  varchar(32000);\r\n");
out.write("declare variable thisfield varchar(1000);\r\n");
//out.write("\r\n");
out.write("begin \r\n");
String[] columnType = { "BLOB SUB_TYPE 1", "CHAR", "VARCHAR",
		"TIMESTAMP", "DECIMAL", "NUMERIC", "DOUBLE PRECISION", "FLOAT",
		"SMALLINT", "INTEGER", "TINYINT", "BIGINT", "REAL", "DOUBLE",
		"BIT", "LONGVARCHAR", "BINARY", "LONGVARBINARY", "DATE", "TIME" };
String[] pk = new String[primaryKey.size()];
String[] pkProperty = new String[primaryKey.size()];
String susunanField="";
String valueInsert="";
for (String kolomStr : namaKolom) {
	susunanField += kolomStr + ",";
	valueInsert+=" || ', ' || iif( NEW."+kolomStr+" is null, 'NULL','''' || NEW."+kolomStr+" || '''') ";
}
susunanField=susunanField.substring(0, susunanField.length()-1);
valueInsert=valueInsert.substring(11, valueInsert.length()-1 );
out.write("thisfield='" +susunanField+ "';\r\n");
out.write("thisvalue= " +valueInsert + " ; \r\n");
out.write("execute procedure writelog('"+tableName+"','INSERT',:thisvalue,:thisfield); \r\n");
out.write("end;\r\n");

//------------------ insert selesai
out.write("\r\n");

out.write("CREATE OR ALTER  TRIGGER "
		+ tableName
		+ "_DELREPL FOR "+tableName+" \r\n");

out.write("ACTIVE AFTER DELETE POSITION 100 \r\n");
out.write("AS \r\n");
out.write("declare variable thisvalue  varchar(32000);\r\n");
out.write("begin \r\n");



int pos = 0;
for (Iterator i = primaryKey.iterator(); i.hasNext();) {
	String containCol = (String) i.next();
	pk[pos] = containCol;
	pkProperty[pos] = "PKey" + ToSentenceCase(containCol);
	pos++;

}
String valueDelete="";

for (int i = 0; i < namaKolom.length; i++) {
	for (int j = 0; j < columnType.length; j++) {
		if (tipeKolom[i].equalsIgnoreCase(columnType[j])) {
		}
	}
	String kolom = namaKolom[i];
	if (IsPrimaryKey(kolom, pk)) {
		valueDelete+="|| iif( OLD." + kolom + " is null, '','" + kolom + "=''' || OLD." + kolom + " || ''' and ') ";
	}
}	
try {
	
valueDelete=valueDelete.substring(2, valueDelete.length()-1);
out.write("thisvalue= "+valueDelete+" ; \r\n");
out.write("execute procedure writelog('"+tableName+"','DELETE',:thisvalue,null); \r\n");
out.write("end;\r\n");
//-------------- delete selesai
out.write("\r\n");

out.write("CREATE OR ALTER  TRIGGER "
		+ tableName
		+ "_UPDREPL FOR "+tableName+" \r\n");

out.write("ACTIVE AFTER UPDATE POSITION 100 \r\n");
out.write("AS \r\n");
out.write("declare variable thisvalue  varchar(32000);\r\n");
out.write("declare variable thisfield varchar(1000);\r\n");
//out.write("\r\n");
out.write("begin \r\n");
out.write("thisvalue= "+valueDelete+" ; \r\n");
String valueUpdate="";
for (String kolomStr : namaKolom) {
	
	valueUpdate+=" || iif( NEW."+kolomStr+" is null, '','"+kolomStr+"=''' || NEW."+kolomStr+" || ''',') ";
}
valueUpdate=valueUpdate.substring(3, valueUpdate.length()-1);
out.write("thisfield= "+valueUpdate + " ; \r\n");
out.write("execute procedure writelog('"+tableName+"','UPDATE',:thisvalue,:thisfield); \r\n");
out.write("end;\r\n");
out.write("\r\n");

} catch (Exception e) {
	// TODO: handle exception
	out.write("end;\r\n");
	System.out.println("masalah di : "+tableName);
	e.printStackTrace();
	
}






}



	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GenTriggerRepl().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jBtnGenerate;
	private javax.swing.JButton jBtnOpenDir;
	private javax.swing.JButton jBtnURL;
	// private com.l2fprod.common.swing.JDirectoryChooser jDirectoryChooser1;
	private JFileChooser jDirectoryChooser1;
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

}
