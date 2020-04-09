package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude= {"comr_image", "comr_img"})
public class ClassDetailPagingVO<T> implements Serializable{

	// 데이터 불러오는 vo
	private Integer comr_cd;
	private Integer commu_cd;
	private String mem_email;
	private String comr_comm_cont;
	private String comr_comm_date;
	private String comr_temp_nm;
	private String comr_ori_nm;
	private Integer comr_size;
	private String comr_fancy;
	private String comr_mime;
	private String rep_mail;
	private String rep_date;
	
	//클래스커뮤니티
	private Integer cl_cd;
	private String commu_cont;
	private String commu_date;
	private String crea_email;
	
	@JsonIgnore
	private transient byte[] comr_img; //byte배열이기 때문에 직렬화 안함
	
	@JsonIgnore
	private transient MultipartFile comr_image;

	//직렬화
	public String getImgBase64() {
		if(comr_img == null) return null;
		return Base64.encodeBase64String(comr_img);
	}
	
	//마샬링
	public void setComr_image(MultipartFile comr_image) throws IOException {
		this.comr_image = comr_image;
		if(comr_image != null)
			comr_img = comr_image.getBytes();
	}

	//	=========================================================== 
	
	//paging 처리
	public ClassDetailPagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int screenSize=5; // 한 화면에서 보여줄 수 있는 레코드 수
	private int blockSize=5; // 한페이지에서 제공할 페이지 링크 수
	private int currentPage; // 클라이언트의 파라미터로 결정됨
	
	private int totalRecord; // db에 있는 전체레코드수
	private int totalPage; // blockSize
	
	//screenSize와 currentPage로
	private long startRow;
	private long endRow; 
	
	//blocksize, currentpage로
	private int startPage;
	private int endPage;
	
	private List<T> dataList; 
	private SearchVO searchVO; // 일반 검색
	
	private T searchDetail;
	
			
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int) Math.ceil(totalRecord / (double) screenSize); 
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = screenSize * currentPage;
		startRow = endRow - (screenSize - 1);
		endPage = blockSize * ((currentPage + (blockSize - 1)) / blockSize);
		startPage = endPage - (blockSize - 1);
	}
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>\n");
		html.append("<ul class='pagination'>\n");
		
		int lastPage = endPage > totalPage ? totalPage : endPage;
		if(startPage >blockSize){
			html.append("<li class='page-item '>\n");
			html.append("<a data-page='" + (startPage - blockSize) + "' class='page-link' href='?page=" + (startPage - blockSize) + "' tabindex='-1' aria-disabled='true'>previous</a>\n");
			html.append("</li>\n");
		}
		for(int i = startPage; i <= lastPage; i++){
			if(i == currentPage){
				html.append("<li class='page-item active' aria-current='page'>\n");
				html.append("<a data-page='" + i + "' class='page-link' href='#'>" + i + "<span class='sr-only'>(current)</span></a>\n");
				html.append("</li>\n");
			} else {
				html.append("<li class='page-item'>\n");
				html.append("<a data-page='" + i + "' class='page-link' href='?page=" + i +"'>" + i + "</a>\n");
				html.append("</li>\n");
			}
		}
		if(lastPage < totalPage){
			html.append("<li class='page-item'>\n");
			html.append("<a data-page='" + (lastPage + 1) + "' class='page-link' href='?page=" + (lastPage + 1) + "'>Next</a>\n");
			html.append("</li>\n");
		}
		html.append("</ul>\n");
		html.append("</nav>\n");
		return html.toString();
	}
	
}
