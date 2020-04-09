package kr.or.ddit.creatorCenter.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CreatorVO;
import kr.or.ddit.vo.CreatorVO2;
import kr.or.ddit.vo.CurriculumVO;
import kr.or.ddit.vo.PaymentVO;

public interface IClassService {
	/** 사전조사 통과 후 클래스생성
	 * @param piVO
	 * @return
	 */
	public ServiceResult createClass(ClassVO cl);
	public ServiceResult modifyClass(ClassVO cl);//안씀
	/** 내가 개설한 클래스 리스트 조회
	 * @param mem_email
	 * @return
	 */
	public List<ClassVO> readMyClassList(String mem_email);
	/**클래스 상세정보 조회
	 * @param cl_cd
	 * @return
	 */
	public ClassVO readMyClass(int cl_cd);
	/**해당클래스의 커리큘럼 조회
	 * @param cl_cd
	 * @return
	 */
	public List<CurriculumVO> readMyCurri(int cl_cd);

	
	/**해당클래스의 크리에이터 정보 조회
	 * @param cl_cd
	 * @return
	 */
	public CreatorVO readCreator(int cl_cd); 
	
	/** 제작자로 등록이 되어있는지 조회
	 * @param mem_email
	 * @return
	 */
	public CreatorVO creatorCheck(String mem_email);
	
	/**제작자로 등록
	 * @param crea
	 * @return
	 */
	public ServiceResult createCreator(CreatorVO2 crea);
	
	/**이 클래스에 결제한 회원인지 확인
	 * @param mem_email
	 * @return
	 */
	public ServiceResult readPaidCheck(PaymentVO pay);
	
	
}
