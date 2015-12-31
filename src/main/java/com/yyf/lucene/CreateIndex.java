package com.yyf.lucene;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.yyf.model.Doc;
import com.yyf.model.User;
import com.yyf.utils.FileUtil;

public class CreateIndex {
	private List<Doc> list;
	private Analyzer analyzer = new SmartChineseAnalyzer();
	private Directory directory;
	private IndexWriter iwriter;
	private Query query;

	public void addIndex(Doc upDoc) throws IOException {
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		directory = FSDirectory.open(new File(FileUtil.RootPath + "index/").toPath());
		iwriter = new IndexWriter(directory, config);
		try {
			Document doc = new Document();
			doc.add(new Field("docId", upDoc.getDocId() + "", TextField.TYPE_STORED));
			doc.add(new Field("docName", upDoc.getDocName(), TextField.TYPE_STORED));
			doc.add(new Field("docOnwerUser", upDoc.getDocOwnUser().getUserName(), TextField.TYPE_STORED));

			if (upDoc.getDocType() != null && upDoc.getDocType().getTypeName() != null) {
				doc.add(new Field("docType", upDoc.getDocType().getTypeName(), TextField.TYPE_STORED));
			} else {
				doc.add(new Field("docType", "", TextField.TYPE_STORED));
			}

			doc.add(new Field("docDate", upDoc.getDocDate().getTime() + "", TextField.TYPE_STORED));
			doc.add(new Field("docDateStr", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(upDoc.getDocDate()),
					TextField.TYPE_STORED));
			if (upDoc.getDocType() != null && upDoc.getDocType().getParentType() != null) {
				doc.add(new Field("parentDocType", upDoc.getDocType().getParentType().getTypeName(),
						TextField.TYPE_STORED));
			} else if (upDoc.getDocType() != null && upDoc.getDocType().getTypeName() != null) {
				doc.add(new Field("parentDocType", upDoc.getDocType().getTypeName(), TextField.TYPE_STORED));

			} else {
				doc.add(new Field("parentDocType", "", TextField.TYPE_STORED));
			}
			doc.add(new Field("docDesc", upDoc.getDocDesc() == null ? "" : upDoc.getDocDesc(), TextField.TYPE_STORED));
			doc.add(new Field("docFoot", upDoc.getDocFoot() == null ? "" : upDoc.getDocFoot(), TextField.TYPE_STORED));
			iwriter.addDocument(doc);
		} catch (Exception e) {
			e.printStackTrace();
			iwriter.close();
			directory.close();
		} finally {
			iwriter.close();
			directory.close();
		}
	}

	public void deleteAll() throws IOException {
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		directory = FSDirectory.open(new File(FileUtil.RootPath + "index/").toPath());
		iwriter = new IndexWriter(directory, config);
		iwriter.deleteAll();
		iwriter.close();
		directory.close();
	}

	public void deleteById(Long id) throws IOException, ParseException {
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		directory = FSDirectory.open(new File(FileUtil.RootPath + "index/").toPath());
		iwriter = new IndexWriter(directory, config);
		QueryParser parser = new QueryParser("docId", analyzer);
		Query query = parser.parse(id + "");
		iwriter.deleteDocuments(query);
		iwriter.close();
		directory.close();
	}

	public List<Doc> getDocSearchList(Integer page, String key) throws IOException, ParseException {
		list = new ArrayList<Doc>();
		directory = FSDirectory.open(new File(FileUtil.RootPath + "index/").toPath());
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		String[] fields = { "docId", "docName", "docOnwerUser", "docType", "docDesc", "parentDocType", "docFoot",
				"docDateStr" };
		MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
		query = parser.parse(key);
		@SuppressWarnings("deprecation")
		ScoreDoc[] hits = isearcher.search(query, null, 20).scoreDocs;// searchAfter(page*10,
																		// query,
																		// 1);
		//
		// Iterate through the results:
		Document hitDoc = null;
		Doc resDoc;
		for (int i = 0; i < hits.length; i++) {
			resDoc = new Doc();
			hitDoc = isearcher.doc(hits[i].doc);
			resDoc.setDocId(Long.valueOf(hitDoc.get("docId")));
			resDoc.setDocName(hitDoc.get("docName"));
			resDoc.setDocDesc(hitDoc.get("docDesc"));

			Date date = null;
			try {
				date = new Date(Long.valueOf(hitDoc.get("docDate")));
			} catch (Exception e) {
			}
			resDoc.setDocDate(date);

			resDoc.setDocFoot(hitDoc.get("docFoot"));
			User user = new User();
			user.setUserName(hitDoc.get("docOnwerUser"));
			resDoc.setDocOwnUser(user);
			// hitDoc.get("")
			list.add(resDoc);
		}
		ireader.close();
		directory.close();
		return list;
	}

}
