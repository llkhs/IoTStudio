pragma solidity >=0.6.10;
pragma experimental ABIEncoderV2;


import "./DateTime.sol";
import "./Uint2str.sol";

contract Main {

    DateTime dateTime = new DateTime();
    Uint2str changeType = new Uint2str();
    
    event Log(uint8);
    
    uint checkid;
    address public owner;
    
    
    struct UserInfo{
        address account;
        uint count;
    }
    
    
    mapping(uint32 => address[]) private MonthCheckUserMap;
    mapping(uint32 => mapping(address => UserInfo)) private MonthCheckMap;
    mapping(address => UserInfo) private CheckInfo;
    mapping(address => bool) private Administrator;

    address[] UserList;
    mapping(address => bool) private ExistUserMap;

    //model
    mapping(uint256 => UserInfo) private UserInfoMap;
    mapping(uint256 => UserInfo[]) private UserInfoGroupMap;
    
    uint[] private res;
    
    
    constructor() public{
        owner= msg.sender;
    }
    
    // modifier onlyOwner() {
    //     require(msg.sender == owner);
    //     _;
    //   }
    
    function checkIn(uint32 _date, address _address, uint _count) external returns(uint){ //
        //string memory _date = getNow();
        if(ExistUserMap[_address] == true){
           if(MonthCheckMap[_date][_address].account == _address){
               MonthCheckMap[_date][_address].count += _count;
               CheckInfo[_address].count += _count;
           }
        }
        else{
            createMonthCheckMap(_date, _address, _count);
        }
        emit Log(200);
        return CheckInfo[_address].count;
    }

    function createMonthCheckMap(uint32 _date, address _address, uint _count) internal returns(uint32 ,address){ //
            UserInfo memory userinfo = UserInfo(_address, _count);
            CheckInfo[_address] = userinfo;
            MonthCheckMap[_date][_address] = userinfo;
            UserList.push(_address);
            MonthCheckUserMap[_date].push(_address);
            ExistUserMap[_address] = true;
            return (_date, UserList[UserList.length-1]);
    }
    
    function checkTotal(address _address, uint32 _firstMonth) view external returns(uint32, uint){ //
        //require(Administrator[_address] == true, "403");
        return Total(_address, _firstMonth);
    }

    function checkTotal(address _address) view external returns(address, uint){ //
        //require(Administrator[_address] == true, "403");
        return (CheckInfo[_address].account, CheckInfo[_address].count);
    }
    
    function Total(address _address, uint32 _date) view internal returns(uint32, uint){  //
        // for(uint i=0; i<=monthCheckMap[_date].length; i++){
        //     return List[_date][msg.sender].count;
        // }
        require(_date > 0, "400");
        //require(keccak256(bytes(changeType.uintToString(_endMonth))) == keccak256(bytes("")), "400");
        //string memory _date = changeType.uintToString(_firstMonth);
        uint num = MonthCheckMap[_date][_address].count;
        // do{
        //     if( (j-12)/100==j/100 ){
        //         j+=88;
        //     }else{
        //         j += 1;
        //     num += List[changeType.uintToString(j)][_address].count;
        //     }
        // }while(j != _endMonth);
        return (_date, num);
    }
    
    function getMonthCheckUsers(uint32 _date) view external returns(address[] memory){   //
        require(_date > 0, "400");
        //require(msg.sender == owner, "403");
        return MonthCheckUserMap[_date];
    }
    
    // function getEveryOneTotal(address _address, uint _firstMonth)external returns(UserInfo[] memory){
    //     require(keccak256(bytes(changeType.uintToString(_firstMonth))) != keccak256(bytes("")), "400");
    //     //require(msg.sender == owner, "403");
    //     // UserInfo[] user_info;
    //     // UserInfo storage newUserInfo = users[1];
    //     // string memory tag = changeType.uintToString(_endMonth - _firstMonth);
    //     UserInfo memory newUserInfo = UserInfoMap[1];
    //     for(uint i=0; i<_addressArray.length; i++){
    //         newUserInfo.account = _addressArray[i];
    //         newUserInfo.count = Total(_addressArray, _firstMonth);
    //         UserInfoGroupMap[1].push(newUserInfo);
    //     }
    //     UserInfo[] memory res = UserInfoGroupMap[1];
    //     return res;
    // }
    
    function getNow() public view returns(string memory){   //
        uint year = dateTime.getYear(block.timestamp);  //2022
        uint month = dateTime.getMonth(block.timestamp);    //06
        string memory date = changeType.uintToString(year * 100 + month);
        return date;
    }
    
}

