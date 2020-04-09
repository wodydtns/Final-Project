package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class hobbyCategoryPagingVO<T>{

	public hobbyCategoryPagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int screenSize=9; // 한 화면에서 보여줄 수 있는 레코드 수
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
	
	/*카테고리 코드*/
	private String cate_cd;
	
	
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
			html.append("<a data-page='" + (startPage - blockSize) + "' class='page-link' href='?what=" + (startPage - blockSize) + "' tabindex='-1' aria-disabled='true'>previous</a>\n");
			html.append("</li>\n");
		}
		for(int i = startPage; i <= lastPage; i++){
			if(i == currentPage){
				html.append("<li class='page-item active' aria-current='page'>\n");
				html.append("<a data-page='" + i + "' class='page-link' href='#'>" + i + "<span class='sr-only'>(current)</span></a>\n");
				html.append("</li>\n");
			} else {
				html.append("<li class='page-item'>\n");
				html.append("<a data-page='" + i + "' class='page-link' href='?what=" + i +"'>" + i + "</a>\n");
				html.append("</li>\n");
			}
		}
		if(lastPage < totalPage){
			html.append("<li class='page-item'>\n");
			html.append("<a data-page='" + (lastPage + 1) + "' class='page-link' href='?what=" + (lastPage + 1) + "'>Next</a>\n");
			html.append("</li>\n");
		}
		html.append("</ul>\n");
		html.append("</nav>\n");
		return html.toString();
	}
}
