<%@page import="i2am.plan.manager.web.CommandSubmitter.ALGORITHM_TYPE"%>
<%@ page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="EUC-KR" import="java.util.*" import="i2am.plan.manager.web.*" import="i2am.plan.manager.web.bean.*"%>
<%
	String user_id = (String) session.getAttribute("user_id");
	String planName = request.getParameter("plan_name");
	String srcName = request.getParameter("src_name");
	String dstName = request.getParameter("dst_name");
	String algorithmType = request.getParameter("algorithm_type");

	CommandSubmitter submitter = new CommandSubmitter();
	
	Algorithm algorithm = null;
	if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.BINARY_BERNOULLI_SAMPLING ) {
		algorithm = new BinaryBernoulliSampling(1, Integer.parseInt(request.getParameter("sample_size")), 
				Integer.parseInt(request.getParameter("window_size")));
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.HASH_SAMPLING ) {
		algorithm = new HashSampling(1, Integer.parseInt(request.getParameter("sample_ratio")), 
				Integer.parseInt(request.getParameter("window_size")), request.getParameter("hash_function")); 
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.PRIORITY_SAMPLING ) {
		algorithm = new PrioritySampling(1, Integer.parseInt(request.getParameter("sample_size")), 
				Integer.parseInt(request.getParameter("window_size")));
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.RESERVOIR_SAMPLING ) {
		algorithm = new ReservoirSampling(1, Integer.parseInt(request.getParameter("sample_size")), 
				Integer.parseInt(request.getParameter("window_size")));
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.STRATIFIED_SAMPLING ) {
		algorithm = new StratifiedSampling(1, Integer.parseInt(request.getParameter("sample_size")), 
				Integer.parseInt(request.getParameter("window_size")));
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.SYSTEMATIC_SAMPLING ) {
		algorithm = new SystematicSampling(1, Integer.parseInt(request.getParameter("sample_ratio"))); 
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.K_SAMPLING ) {
		algorithm = new KSampling(1, Integer.parseInt(request.getParameter("sample_ratio"))); 
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.UC_K_SAMPLING ) {
		algorithm = new UCKSampling(1, Integer.parseInt(request.getParameter("sample_ratio")),
				Double.parseDouble(request.getParameter("uc_under_bound"))); 
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.QUERY_FILTERING ) {
		algorithm = new QueryFiltering(1, request.getParameter("keywords"));
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.BLOOM_FILTERING ) {
		algorithm = new BloomFiltering(1, Integer.parseInt(request.getParameter("bucket_size")),
				request.getParameter("keywords"));
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.KALMAN_FILTERING ) {
		algorithm = new KalmanFiltering(1, 
				Double.parseDouble(request.getParameter("q_val")), Double.parseDouble(request.getParameter("r_val"))); 
	} else if ( ALGORITHM_TYPE.valueOf(algorithmType) == ALGORITHM_TYPE.NOISE_RECOMMEND_KALMAN_FILTERING ) {
		algorithm = new NoiseRecommendKalmanFiltering(1, Double.parseDouble(request.getParameter("q_val"))); 
	}
	
	List<Algorithm> algorithms = new ArrayList<Algorithm>();
	algorithms.add(algorithm);
	submitter.createPlan(user_id, planName, srcName, dstName, algorithms);
	
	submitter.submit();
	String command = submitter.printCommand();
%>
<%=command %>
