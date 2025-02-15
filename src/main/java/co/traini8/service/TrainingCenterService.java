package co.traini8.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.traini8.entity.TrainingCenter;

public interface TrainingCenterService {

	public CompletableFuture<TrainingCenter> createCenter(TrainingCenter trainingCenter);

	public CompletableFuture<List<TrainingCenter>> createCenters(List<TrainingCenter> trainingCenters);

	public Page<TrainingCenter> searchCenter(String centerName, String centerCode, Pageable pageable);
}
