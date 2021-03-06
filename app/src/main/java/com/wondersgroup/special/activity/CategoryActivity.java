package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;

import java.util.HashMap;

public class CategoryActivity extends BaseActivity {
    private TextView mTextBoiler, mTextVessel, mTextCylindex, mTextPipe, mTextElevator, mTextLifting, mTextVehicle, mTextFacility;
    private TextView mBoilerCategory, mBoilerArea, mBoilerFuel, mBoilerPressure;
    private TextView mVesselCategory, mVesselArea, mVesselPurpose, mVesselEquipment;
    private TextView mPipeCategory, mPipeMedium;
    private TextView mElevatorCategory, mElevatorArea, mElevatorDistribution, mElevatorServiceLife,
            mElevatorEvaluation, mElevatorGrowth, mElevatorMaintenance, mElevatorRenovation;
    private TextView mLiftingCategory, mLiftingArea, mLiftingTonnage;
    private TextView mVehicleCategory;
    private TextView mFacilityCategory, mFacilityLevel;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_category);
        mTitle.setText("特种设备");
        findViewById(R.id.relative_boiler).setOnClickListener(this);
        findViewById(R.id.relative_vessel).setOnClickListener(this);
        findViewById(R.id.relative_cylindex).setOnClickListener(this);
        findViewById(R.id.relative_pipe).setOnClickListener(this);
        findViewById(R.id.relative_elevator).setOnClickListener(this);
        findViewById(R.id.relative_lifting).setOnClickListener(this);
        findViewById(R.id.relative_vehicle).setOnClickListener(this);
        findViewById(R.id.relative_facility).setOnClickListener(this);
        findViewById(R.id.relative_transport).setOnClickListener(this);

        mBoilerCategory = (TextView) findViewById(R.id.boiler_category);
        mBoilerCategory.setOnClickListener(this);
        mBoilerArea = (TextView) findViewById(R.id.boiler_area);
        mBoilerArea.setOnClickListener(this);
        mBoilerFuel = (TextView) findViewById(R.id.boiler_fuel);
        mBoilerFuel.setOnClickListener(this);
        mBoilerPressure = (TextView) findViewById(R.id.boiler_pressure);
        mBoilerPressure.setOnClickListener(this);

        mVesselCategory = (TextView) findViewById(R.id.vessel_category);
        mVesselCategory.setOnClickListener(this);
        mVesselArea = (TextView) findViewById(R.id.vessel_area);
        mVesselArea.setOnClickListener(this);
        mVesselEquipment = (TextView) findViewById(R.id.vessel_equipment);
        mVesselEquipment.setOnClickListener(this);
        mVesselPurpose = (TextView) findViewById(R.id.vessel_purpose);
        mVesselPurpose.setOnClickListener(this);

        mPipeCategory = (TextView) findViewById(R.id.pipe_category);
        mPipeCategory.setOnClickListener(this);
        mPipeMedium = (TextView) findViewById(R.id.pipe_medium);
        mPipeMedium.setOnClickListener(this);

        mElevatorCategory = (TextView) findViewById(R.id.elevator_category);
        mElevatorCategory.setOnClickListener(this);
        mElevatorArea = (TextView) findViewById(R.id.elevator_area);
        mElevatorArea.setOnClickListener(this);
        mElevatorDistribution = (TextView) findViewById(R.id.elevator_distribution);
        mElevatorDistribution.setOnClickListener(this);
        mElevatorServiceLife = (TextView) findViewById(R.id.elevator_service_life);
        mElevatorServiceLife.setOnClickListener(this);
        mElevatorEvaluation = (TextView) findViewById(R.id.elevator_evaluation);
        mElevatorEvaluation.setOnClickListener(this);
        mElevatorGrowth = (TextView) findViewById(R.id.elevator_growth);
        mElevatorGrowth.setOnClickListener(this);
        mElevatorMaintenance = (TextView) findViewById(R.id.elevator_maintenance);
        mElevatorMaintenance.setOnClickListener(this);
        mElevatorRenovation = (TextView) findViewById(R.id.elevator_renovation);
        mElevatorRenovation.setOnClickListener(this);

        mLiftingCategory = (TextView) findViewById(R.id.lifting_category);
        mLiftingCategory.setOnClickListener(this);
        mLiftingArea = (TextView) findViewById(R.id.lifting_area);
        mLiftingArea.setOnClickListener(this);
        mLiftingTonnage = (TextView) findViewById(R.id.lifting_tonnage);
        mLiftingTonnage.setOnClickListener(this);

        mVehicleCategory = (TextView) findViewById(R.id.vehicle_category);
        mVehicleCategory.setOnClickListener(this);

        mFacilityCategory = (TextView) findViewById(R.id.facility_category);
        mFacilityCategory.setOnClickListener(this);
        mFacilityLevel = (TextView) findViewById(R.id.facility_level);
        mFacilityLevel.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.relative_boiler:
                intent.setClass(CategoryActivity.this, OverviewActivity.class);
                bundle.putString("TYPE", Constant.Overview.BOILER);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_vessel:
                intent.setClass(CategoryActivity.this, OverviewActivity.class);
                bundle.putString("TYPE", Constant.Overview.VESSEL);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_cylindex:
                intent.setClass(CategoryActivity.this, OverviewActivity.class);
                bundle.putString("TYPE", Constant.Overview.CYLINDER);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_pipe:
                intent.setClass(CategoryActivity.this, OverviewActivity.class);
                bundle.putString("TYPE", Constant.Overview.PIPE);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_elevator:
                intent.setClass(CategoryActivity.this, OverviewActivity.class);
                bundle.putString("TYPE", Constant.Overview.ELEVATOR);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_lifting:
                intent.setClass(CategoryActivity.this, OverviewActivity.class);
                bundle.putString("TYPE", Constant.Overview.LIFTING);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_vehicle:
                intent.setClass(CategoryActivity.this, OverviewActivity.class);
                bundle.putString("TYPE", Constant.Overview.VEHICLE);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_facility:
                intent.setClass(CategoryActivity.this, OverviewActivity.class);
                bundle.putString("TYPE", Constant.Overview.FACILITY);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_transport:
                HashMap<String, String> params = new HashMap<>();
                intent.setClass(CategoryActivity.this, EquipmentListActivity.class);
                params.put("type", Constant.Overview.TRANSPORT);
                params.put("orderNum", Constant.Transport.TRANSPORT);
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.HomeDateType.TYPE_ANALYSIS);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.boiler_category:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.BOILER);
                bundle.putString("CHILD_TYPE", Constant.Boiler.CATEGORY);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.boiler_area:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.BOILER);
                bundle.putString("CHILD_TYPE", Constant.Boiler.AREA);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.boiler_fuel:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.BOILER);
                bundle.putString("CHILD_TYPE", Constant.Boiler.FUEL);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.boiler_pressure:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.BOILER);
                bundle.putString("CHILD_TYPE", Constant.Boiler.PRESSURE);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.vessel_category:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.VESSEL);
                bundle.putString("CHILD_TYPE", Constant.Vessel.CATEGORY);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.vessel_area:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.VESSEL);
                bundle.putString("CHILD_TYPE", Constant.Vessel.AREA);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.vessel_equipment:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.VESSEL);
                bundle.putString("CHILD_TYPE", Constant.Vessel.EQUIPMENT);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.vessel_purpose:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.VESSEL);
                bundle.putString("CHILD_TYPE", Constant.Vessel.PURPOSE);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.pipe_category:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.PIPE);
                bundle.putString("CHILD_TYPE", Constant.Pipe.CATEGORY);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.pipe_medium:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.PIPE);
                bundle.putString("CHILD_TYPE", Constant.Pipe.MEDIUM);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.elevator_category:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.ELEVATOR);
                bundle.putString("CHILD_TYPE", Constant.Elevator.CATEGORY);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.elevator_area:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.ELEVATOR);
                bundle.putString("CHILD_TYPE", Constant.Elevator.AREA);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.elevator_distribution:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.ELEVATOR);
                bundle.putString("CHILD_TYPE", Constant.Elevator.DISTRIBUTION);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.elevator_service_life:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.ELEVATOR);
                bundle.putString("CHILD_TYPE", Constant.Elevator.SERVICE_LIFE);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.elevator_evaluation:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.ELEVATOR);
                bundle.putString("CHILD_TYPE", Constant.Elevator.EVALUATION);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.elevator_growth:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.ELEVATOR);
                bundle.putString("CHILD_TYPE", Constant.Elevator.GROWTH);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.elevator_maintenance:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.ELEVATOR);
                bundle.putString("CHILD_TYPE", Constant.Elevator.MAINTENANCE);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.elevator_renovation:
                intent.setClass(CategoryActivity.this, ElevatorRenovationActivity.class);
//                bundle.putString("PARENT_TYPE", Constant.Overview.ELEVATOR);
//                bundle.putString("CHILD_TYPE", Constant.Elevator.RENOVATION);
//                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.lifting_category:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.LIFTING);
                bundle.putString("CHILD_TYPE", Constant.Lifting.CATEGORY);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.lifting_area:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.LIFTING);
                bundle.putString("CHILD_TYPE", Constant.Lifting.AREA);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.lifting_tonnage:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.LIFTING);
                bundle.putString("CHILD_TYPE", Constant.Lifting.TONNAGE);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.vehicle_category:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.VEHICLE);
                bundle.putString("CHILD_TYPE", Constant.Vehicle.CATEGORY);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.facility_category:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.FACILITY);
                bundle.putString("CHILD_TYPE", Constant.Facility.CATEGORY);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.facility_level:
                intent.setClass(CategoryActivity.this, AnalysisActivity.class);
                bundle.putString("PARENT_TYPE", Constant.Overview.FACILITY);
                bundle.putString("CHILD_TYPE", Constant.Facility.LEVEL);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
