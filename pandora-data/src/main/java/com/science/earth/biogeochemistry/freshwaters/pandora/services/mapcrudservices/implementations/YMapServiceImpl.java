package com.science.earth.biogeochemistry.freshwaters.pandora.services.mapcrudservices.implementations;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.science.earth.biogeochemistry.freshwaters.pandora.general.objects.CellBaseObject;

@Service
public class YMapServiceImpl implements YMapService {
    
    protected Map<Integer, double[]> map = new HashMap<>();
    
    @Override
    public double[] findAtCellAndTimestep(CellBaseObject cell, LocalDateTime t) {
	return map.get(hashCellAndTime(cell, t));
    }

    @Override
    public void saveAtCellAndTimestep(CellBaseObject cell, LocalDateTime tEnd, double[] yEnd) {
	map.put(hashCellAndTime(cell, tEnd), yEnd);
    }
    
    private int hashCellAndTime(CellBaseObject cell, LocalDateTime t) {
	return Objects.hash(cell, t);
    }
}
