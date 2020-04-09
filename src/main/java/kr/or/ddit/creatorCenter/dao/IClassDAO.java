package kr.or.ddit.creatorCenter.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.CreatorVO;
import kr.or.ddit.vo.CreatorVO2;
import kr.or.ddit.vo.CurriculumVO;
import kr.or.ddit.vo.PaymentVO;
@Repository
public interface IClassDAO {
	public int insertClass(ClassVO cl); //얘는 사전조사 후에 이루어져야함
	public int updateClass(ClassVO cl);
	public List<ClassVO> selectMyClassList(String mem_email);//내가 진행중인 클래스 조회
	public ClassVO selectMyClass(int cl_cd);
	public List<CurriculumVO> selectMyCurri(int cl_cd);
	public CreatorVO selectCreator(int cl_cd);
	public CreatorVO creatorCheck(String mem_email);
	public int insertCreator(CreatorVO2 crea);
	public int selectPaidCheck(PaymentVO pay);
}
