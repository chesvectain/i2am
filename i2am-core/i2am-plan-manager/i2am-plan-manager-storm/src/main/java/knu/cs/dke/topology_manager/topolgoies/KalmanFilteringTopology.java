package knu.cs.dke.topology_manager.topolgoies;

import java.io.IOException;

import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.NotAliveException;
import org.apache.storm.thrift.TException;
import org.apache.storm.thrift.transport.TTransportException;

public class KalmanFilteringTopology extends ASamplingFilteringTopology {

	private double q_val;
	private double r_val;	
	
	private RemoteStormController storm;
		
	public KalmanFilteringTopology(String createdTime, String plan, int index, String topologyType, Double q_val, Double r_val) throws TTransportException {
		super(createdTime, plan, index, topologyType);

		this.q_val = q_val;
		this.r_val = r_val;	
		storm = new RemoteStormController();
	}

	@Override
	public void killTopology() throws NotAliveException, AuthorizationException, TException, InterruptedException {
		// TODO Auto-generated method stub
		storm.killTopology(super.getTopologyName());
	}

	@Override
	public void avtivateTopology() throws NotAliveException, AuthorizationException, TException, InterruptedException {
		// TODO Auto-generated method stub
		storm.activateTopology(super.getTopologyName());
		
	}

	@Override
	public void deactivateTopology() throws NotAliveException, AuthorizationException, TException, InterruptedException {
		// TODO Auto-generated method stub
		storm.deactivateTopology(super.getTopologyName());
	}

	@Override
	public void submitTopology() throws InvalidTopologyException, AuthorizationException, TException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		storm.runTopology(this);		
	}

	public double getQ_val() {
		return q_val;
	}

	public void setQ_val(double q_val) {
		this.q_val = q_val;
	}

	public double getR_val() {
		return r_val;
	}

	public void setR_val(double r_val) {
		this.r_val = r_val;
	}
}
