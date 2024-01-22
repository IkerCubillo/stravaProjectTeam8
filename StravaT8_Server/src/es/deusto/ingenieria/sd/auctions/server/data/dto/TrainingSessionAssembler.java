package es.deusto.ingenieria.sd.auctions.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.TrainingSession;

public class TrainingSessionAssembler {
	private static TrainingSessionAssembler instance;

	private TrainingSessionAssembler() { }
	
	public static TrainingSessionAssembler getInstance() {
		if (instance == null) {
			instance = new TrainingSessionAssembler();
		}

		return instance;
	}

	public TrainingSessionDTO trainingSessionToDTO(TrainingSession trainingSession) {
		TrainingSessionDTO dto = new TrainingSessionDTO();
		
		dto.setTitle(trainingSession.getTitle());
		dto.setSport(trainingSession.getSport());
		dto.setDistance(trainingSession.getDistance());
		dto.setStartDate(trainingSession.getStartDate());
		dto.setStartTime(trainingSession.getStartTime());
		dto.setDuration(trainingSession.getDuration());
//		dto.setUser(trainingSession.getUser());
		
		return dto;
	}
	
	public List<TrainingSessionDTO> trainingSessionToDTO(List<TrainingSession> trainingSessions) {
		List<TrainingSessionDTO> dtos = new ArrayList<>();
		
		for (TrainingSession trainingSession : trainingSessions) {
			dtos.add(this.trainingSessionToDTO(trainingSession));
		}
		
		return dtos;		
	}
}