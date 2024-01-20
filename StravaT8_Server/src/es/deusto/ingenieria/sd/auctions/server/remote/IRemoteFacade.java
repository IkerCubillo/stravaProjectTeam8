package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;


	public interface IRemoteFacade extends Remote {	
	
		public long login(String account, String email, String password) throws RemoteException;
		
		public void logout(long token) throws RemoteException;
		
		public void register(String email, String name, String account, Date birthDate, float weight, float height, int mBPM, int bpm) throws RemoteException;
		
		public List<ChallengeDTO> getChallenges() throws RemoteException;
		
		public List<ChallengeDTO> getUserChallenges(long token) throws RemoteException;
		
		public List<TrainingSessionDTO> getTrainingSessions(long token) throws RemoteException;
		
		public boolean acceptChallenge(long token, ChallengeDTO c) throws RemoteException;
		
		public boolean createSession(long token, String title, String sport, float Distance, Date startDate, LocalTime timeStart, float duration) throws RemoteException;
		
		public boolean setUpDistanceChallenge(long token, String name, Date start, Date end, float metric, String sportType) throws RemoteException;
		
		public boolean setUpActivityTimeChallenge(long token, String name, Date start, Date end, float metric, String sportType) throws RemoteException;

}