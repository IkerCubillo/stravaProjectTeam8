package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.sql.Time;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;


	public interface IRemoteFacade extends Remote {	
	
		public long login(String email, String password) throws RemoteException;
		
		public void logout(long token) throws RemoteException;
		
		public void register(String account, String email, String name, Date birthDate, float weight, float height, int mBPM, int bpm) throws RemoteException;
		
		public List<ChallengeDTO> getChallenges() throws RemoteException;
		
		public List<TrainingSessionDTO> getTrainingSessions() throws RemoteException;
		
		public boolean acceptChallenge(ChallengeDTO c) throws RemoteException;
		
		public void createSession(String title, String sport, float Distance, Date startDate, Time timeStart, float duration) throws RemoteException;
		
		public boolean setupDistanceChallenge(String name, Date start, Date end, float metric, String sportType) throws RemoteException;
		
		public boolean setupActivityTimeChallenge(String name, Date start, Date end, float metric, String sportType) throws RemoteException;

}