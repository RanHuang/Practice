%% ���ݣ���������
reservoir_capacity = [3.41,5.44,8.17,11.98,17.18,24.06,32.96,44.23];   
% ˮλ���ף�
reservoir_level = [135,140,145,150,155,160,165,170];
CapacityLevel = polyfit(reservoir_capacity,reservoir_level,2);
LevelCapacity = polyfit(reservoir_level,reservoir_capacity,2);
%% ��й����������/�ף�
discharged_flow = [160,220,290,380,520,740,1000];
% ����ˮλ���ף�
downstream_level = [64,64.5,65,65.5,66,66.5,67];
DischargedLevel = polyfit(discharged_flow,downstream_level,2);
LevelDischarged = polyfit(downstream_level,discharged_flow,2);
%% ����ˮ����ݺ���й������ȡ������ˮλ
up_level = polyval(CapacityLevel,reservoir_capacity);
down_level = polyval(DischargedLevel,discharged_flow);
%% ˮ�ⷢ���Ż�����--��������
NormalLevel = 160;         % ������ˮλ����λ���ף�
DeadLevel = 136;           % ��ˮλ����λ���ף�
FloodLevel = 155;          % Ѵ��ˮλ����λ���ף�
DesignProbability = 0.95;  % ˮ��վ��Ʊ�֤��
GuaranteedOutput = 12.5*10^4;   % ��֤��������λ��ǧ�ߣ�
InstalledCapacity = 32*10^4;    % װ����������λ��ǧ�ߣ�
OutputFactor = 8.5;        % �ۺϳ���ϵ��
%�������������/�룩 �·ݣ�5,6,7...,1,2,3,4
Inflow = [485,390,590,602, 488,350,121,55, 63,48,105,240];
%���κ��˹����ˮ��������/�룩 �·ݣ�5,6,7...,1,2,3,4
WaterDemand = [440,360,300,260, 240,200,200,200, 200,200,200,320];
%% �������
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
% ˮλ���㲽������λ���ף�
STEP = 0.05; 
%% Ԥ��������ռ�
MaxNum = ceil((NormalLevel - DeadLevel)/STEP)+1;
Months = 12;
ScheduleWaterLine = zeros(Months,MaxNum);
Output = zeros(Months,MaxNum);
DischargedFlow = zeros(Months,MaxNum);
PowerFlow = zeros(Months,MaxNum);
AbandomFlow = zeros(Months,MaxNum);
ValidNum = zeros(1,12);
MaxOutput = zeros(Months,MaxNum);
% ��¼���ž���·��
OptimalPath = zeros(Months,MaxNum);
%% ĩ��
MinLevel = MIN_LEVEL(12);
MaxLevel = MAX_LEVEL(12);
index = 0;
for up_level = MinLevel:STEP:MaxLevel  % up_level--����ĩ���³�ˮ��ˮλ
    % ��йˮ����
    next_capacity = polyval(LevelCapacity,DeadLevel);
    discharged = (polyval(LevelCapacity,up_level) - next_capacity)*10^8/(3600*24*30) + Inflow(12);
    if discharged < WaterDemand(12)
        continue;
    end
    down_level = polyval(DischargedLevel,discharged); %����ˮλ
    avg_level = (up_level + DeadLevel)/2.0; %ˮ��ƽ��ˮλ
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
    ScheduleWaterLine(12,index) = up_level;  % ����ĩ���³�ˮ��ˮλ
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
            % ��йˮ����
            discharged = (polyval(LevelCapacity,up_level) - next_capacity)*10^8/(3600*24*30) + Inflow(round);
            if discharged < WaterDemand(round)
                continue;
            end
            avg_level = (up_level + ScheduleWaterLine(round+1,i))/2.0; %ˮ��ƽ��ˮλ
            waterhead = avg_level - down_level; % ˮͷ��ˮ��-���� ˮλ�
            output = 8.5 * discharged * waterhead; % ����
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
            ScheduleWaterLine(round,index) = record_ScheduleWaterLine;  % ����ĩ���³�ˮ��ˮλ
            DischargedFlow(round,index) = record_DischargedFlow;
            AbandomFlow(round,index) =  record_AbandomFlow;

            OptimalPath(round,index)=record_path;
            MaxOutput(round,index)=max_output;
            ValidNum(round) = index;
        end
    end
end
%% ��ʼ״̬
round = 1;
index = 0;
max_output = 0;
start_record_path = 0;
for i=1:ValidNum(2)
    next_capacity = polyval(LevelCapacity,ScheduleWaterLine(round+1,i));
    % ��йˮ����
    discharged = (polyval(LevelCapacity, DeadLevel) - next_capacity)*10^8/(3600*24*30) + Inflow(1);
    if discharged < WaterDemand(round)
        continue;
    end
    up_level = DeadLevel;
    avg_level = (up_level + ScheduleWaterLine(round+1,i))/2.0; %ˮ��ƽ��ˮλ
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
    ScheduleWaterLine(round,index) = up_level;  % ����ĩ���³�ˮ��ˮλ
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
%% ������
i = 1;
record_path = start_record_path;
% fprintf('�ܳ���:%f\n', MaxOutput(i,record_path));
fprintf('��̬�滮ˮλ�ߵ��Ȳ���:%f��\n', STEP);
fprintf('�·�:%d; �³�ˮλ��:%f; ����:%f; ��й����:%f; ��������:%f; ��ˮ����:%f\n', i+4, ScheduleWaterLine(i,record_path), Output(i,record_path)/10^4, DischargedFlow(i,record_path), PowerFlow(i,record_path), AbandomFlow(i,record_path));     

sum_output = Output(i,record_path);
for i=1:7
    record_path = OptimalPath(i,record_path);
    fprintf('�·�:%d; �³�ˮλ��:%f; ����:%f; ��й����:%f; ��������:%f; ��ˮ����:%f\n', i+1+4, ScheduleWaterLine(i+1,record_path), Output(i+1,record_path)/10^4, DischargedFlow(i+1,record_path), PowerFlow(i+1,record_path), AbandomFlow(i+1,record_path));
    sum_output = sum_output + Output(i+1,record_path);
end
for i=8:11
    record_path = OptimalPath(i,record_path);
    fprintf('�·�:%d; �³�ˮλ��:%f; ����:%f; ��й����:%f; ��������:%f; ��ˮ����:%f\n', i+1+4-12, ScheduleWaterLine(i+1,record_path), Output(i+1,record_path)/10^4, DischargedFlow(i+1,record_path), PowerFlow(i+1,record_path), AbandomFlow(i+1,record_path));
    sum_output = sum_output + Output(i+1,record_path);
end
fprintf('�ܳ���:%f��ǧ��\n', sum_output/10^4);