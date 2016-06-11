%% 库容（亿立方）
reservoir_capacity = [3.41,5.44,8.17,11.98,17.18,24.06,32.96,44.23];   
% 水位（米）
reservoir_level = [135,140,145,150,155,160,165,170];
CapacityLevel = polyfit(reservoir_capacity,reservoir_level,2);
LevelCapacity = polyfit(reservoir_level,reservoir_capacity,2);
%% 下泄流量（立方/米）
discharged_flow = [160,220,290,380,520,740,1000];
% 下游水位（米）
downstream_level = [64,64.5,65,65.5,66,66.5,67];
DischargedLevel = polyfit(discharged_flow,downstream_level,2);
LevelDischarged = polyfit(downstream_level,discharged_flow,2);
%% 根据水库库容和下泄流量获取上下游水位
up_level = polyval(CapacityLevel,reservoir_capacity);
down_level = polyval(DischargedLevel,discharged_flow);
%% 水库发电优化问题--常量定义
NormalLevel = 160;         % 正常蓄水位（单位：米）
DeadLevel = 136;           % 死水位（单位：米）
FloodLevel = 155;          % 汛期水位（单位：米）
DesignProbability = 0.95;  % 水电站设计保证率
GuaranteedOutput = 12.5*10^4;   % 保证出力（单位：千瓦）
InstalledCapacity = 32*10^4;    % 装机容量（单位：千瓦）
OutputFactor = 8.5;        % 综合出力系数
%入库流量（立方/秒） 月份：5,6,7...,1,2,3,4
Inflow = [485,390,590,602, 488,350,121,55, 63,48,105,240];
%下游航运灌溉需水量（立方/秒） 月份：5,6,7...,1,2,3,4
WaterDemand = [440,360,300,260, 240,200,200,200, 200,200,200,320];
%% 计算出力
%function output = CalculateOutput(flow,waterhead)
%    output = 8.5*flow*waterhead/10^4;
%end
%% 
MAX_LEVEL = zeros(1,12);
MIN_LEVEL = zeros(1,12);

MAX_LEVEL(1) = NormalLevel;
MIN_LEVEL(1) = DeadLevel;
for i= 2:5
    MAX_LEVEL(i) = FloodLevel;
    MIN_LEVEL(i) = DeadLevel;
end
for i= 6:12
    MAX_LEVEL(i) = NormalLevel;
    MIN_LEVEL(i) = DeadLevel;
end
% 水位计算步长（单位：米）
STEP = 0.05; 
%% 预分配变量空间
MaxNum = ceil((NormalLevel - DeadLevel)/STEP)+1;
Months = 12;
ScheduleWaterLine = zeros(Months,MaxNum);
Output = zeros(Months,MaxNum);
DischargedFlow = zeros(Months,MaxNum);
PowerFlow = zeros(Months,MaxNum);
AbandomFlow = zeros(Months,MaxNum);
ValidNum = zeros(1,12);
MaxOutput = zeros(Months,MaxNum);
% 记录最优决策路径
OptimalPath = zeros(Months,MaxNum);
%% 末月
MinLevel = MIN_LEVEL(12);
MaxLevel = MAX_LEVEL(12);
index = 0;
for up_level = MinLevel:STEP:MaxLevel  % up_level--上月末或本月初水库水位
    % 下泄水流量
    next_capacity = polyval(LevelCapacity,DeadLevel);
    discharged = (polyval(LevelCapacity,up_level) - next_capacity)*10^8/(3600*24*30) + Inflow(12);
    if discharged < WaterDemand(12)
        continue;
    end
    down_level = polyval(DischargedLevel,discharged); %下游水位
    avg_level = (up_level + DeadLevel)/2.0; %水库平均水位
    waterhead = avg_level - down_level;
    output = 8.5 * discharged * waterhead;
    if output < GuaranteedOutput
        continue;
    elseif output > InstalledCapacity
        index = index + 1;
        Output(12,index) = InstalledCapacity;
        PowerFlow(12,index) = InstalledCapacity/(8.5*waterhead);
    else
        index = index + 1;
        Output(12,index) = output;
        PowerFlow(12,index) = discharged;
    end
    ScheduleWaterLine(12,index) = up_level;  % 上月末或本月初水库水位
    DischargedFlow(12,index) = discharged;
    AbandomFlow(12,index) =  DischargedFlow(12,index) - PowerFlow(12,index);
    ValidNum(12) = index;

    OptimalPath(12,index)=index;
    MaxOutput(12,index)=Output(12,index);
end
%%
for round=11:-1:2
    MaxLevel = MAX_LEVEL(round);
    MinLevel = MIN_LEVEL(round);
    index = 0;
    for up_level=MinLevel:STEP:MaxLevel
        index = index+1;
        max_output = 0;
        for i=1:ValidNum(round+1)
            next_capacity = polyval(LevelCapacity,ScheduleWaterLine(round+1,i));
            % 下泄水流量
            discharged = (polyval(LevelCapacity,up_level) - next_capacity)*10^8/(3600*24*30) + Inflow(round);
            if discharged < WaterDemand(round)
                continue;
            end
            avg_level = (up_level + ScheduleWaterLine(round+1,i))/2.0; %水库平均水位
            waterhead = avg_level - down_level; % 水头（水库-下游 水位差）
            output = 8.5 * discharged * waterhead; % 出力
            if output < GuaranteedOutput
                continue;
            elseif output > InstalledCapacity
                temp_output = InstalledCapacity;
                temp_powerflow = InstalledCapacity/(8.5*waterhead);                
            else
                temp_output = output;
                temp_powerflow = discharged;
            end
            if max_output < temp_output + MaxOutput(round+1,i)
                max_output = temp_output + MaxOutput(round+1,i);
                record_Output = temp_output;
                record_PowerFlow = temp_powerflow;
                record_ScheduleWaterLine = up_level;
                record_DischargedFlow = discharged;
                record_AbandomFlow = record_DischargedFlow - record_PowerFlow;
                record_path = i;
            end
        end
        if max_output > 0
            Output(round,index) = record_Output;
            PowerFlow(round,index) = record_PowerFlow;
            ScheduleWaterLine(round,index) = record_ScheduleWaterLine;  % 上月末或本月初水库水位
            DischargedFlow(round,index) = record_DischargedFlow;
            AbandomFlow(round,index) =  record_AbandomFlow;

            OptimalPath(round,index)=record_path;
            MaxOutput(round,index)=max_output;
            ValidNum(round) = index;
        end
    end
end
%% 初始状态
round = 1;
index = 0;
max_output = 0;
start_record_path = 0;
for i=1:ValidNum(2)
    next_capacity = polyval(LevelCapacity,ScheduleWaterLine(round+1,i));
    % 下泄水流量
    discharged = (polyval(LevelCapacity, DeadLevel) - next_capacity)*10^8/(3600*24*30) + Inflow(1);
    if discharged < WaterDemand(round)
        continue;
    end
    up_level = DeadLevel;
    avg_level = (up_level + ScheduleWaterLine(round+1,i))/2.0; %水库平均水位
    waterhead = avg_level - down_level;
    output = 8.5 * discharged * waterhead;
    if output < GuaranteedOutput
        continue;
    elseif output > InstalledCapacity
        index = index + 1;
        Output(round,index) = InstalledCapacity;
        PowerFlow(round,index) = InstalledCapacity/(8.5*waterhead);              
    else
        index = index + 1;
        Output(round,index) = output;
        PowerFlow(round,index) = discharged;
    end
    ScheduleWaterLine(round,index) = up_level;  % 上月末或本月初水库水位
    DischargedFlow(round,index) = discharged;
    AbandomFlow(round,index) =  DischargedFlow(round,index) - PowerFlow(round,index);
    ValidNum(round) = index;

    temp_output = Output(round,index);
    temp_powerflow =  PowerFlow(round,index);  
    if max_output < temp_output + MaxOutput(round+1,i)
        max_output = temp_output + MaxOutput(round+1,i);
        record_Output = temp_output;
        record_PowerFlow = temp_powerflow;
        record_ScheduleWaterLine = up_level;
        record_DischargedFlow = discharged;
        record_AbandomFlow = record_DischargedFlow - record_PowerFlow;
        record_path = i;
        record_index = index;
    end
end
if max_output > 0
    start_record_path = record_index;
    OptimalPath(round,start_record_path)=record_path;
    MaxOutput(round,start_record_path)=max_output;
end
%% 输出结果
i = 1;
record_path = start_record_path;
% fprintf('总出力:%f\n', MaxOutput(i,record_path));
fprintf('动态规划水位线调度步长:%f米\n', STEP);
fprintf('月份:%d; 月初水位线:%f; 出力:%f; 下泄流量:%f; 发电流量:%f; 弃水流量:%f\n', i+4, ScheduleWaterLine(i,record_path), Output(i,record_path)/10^4, DischargedFlow(i,record_path), PowerFlow(i,record_path), AbandomFlow(i,record_path));     

sum_output = Output(i,record_path);
for i=1:7
    record_path = OptimalPath(i,record_path);
    fprintf('月份:%d; 月初水位线:%f; 出力:%f; 下泄流量:%f; 发电流量:%f; 弃水流量:%f\n', i+1+4, ScheduleWaterLine(i+1,record_path), Output(i+1,record_path)/10^4, DischargedFlow(i+1,record_path), PowerFlow(i+1,record_path), AbandomFlow(i+1,record_path));
    sum_output = sum_output + Output(i+1,record_path);
end
for i=8:11
    record_path = OptimalPath(i,record_path);
    fprintf('月份:%d; 月初水位线:%f; 出力:%f; 下泄流量:%f; 发电流量:%f; 弃水流量:%f\n', i+1+4-12, ScheduleWaterLine(i+1,record_path), Output(i+1,record_path)/10^4, DischargedFlow(i+1,record_path), PowerFlow(i+1,record_path), AbandomFlow(i+1,record_path));
    sum_output = sum_output + Output(i+1,record_path);
end
fprintf('总出力:%f万千瓦\n', sum_output/10^4);