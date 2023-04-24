import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faDollarSign,
  faQuestion,
  faHeart,
  faCircleUser,
  faChevronDown,
} from "@fortawesome/free-solid-svg-icons";
import "./topbar.scss";

function TopBar() {
  return (
    <>
      <div className="d-flex justify-content-between w-100 top_bar mx-4">
        <div className="col-6">
          <h3>Nhóm khách hàng</h3>
        </div>
        <div className="d-flex justify-content-between col-6 h-100 align-items-center">
          <div className="d-flex justify-content-between top_bar_item">
            <div className="me-2">
              <FontAwesomeIcon icon={faDollarSign} />
            </div>
            <div>Vay vốn kinh doanh</div>
          </div>
          <div className="d-flex justify-content-between top_bar_item">
            <div className="me-2">
              <FontAwesomeIcon icon={faQuestion} />
            </div>
            <div>Trợ giúp</div>
          </div>
          <div className="d-flex justify-content-between top_bar_item">
            <div className="me-2">
              <FontAwesomeIcon icon={faHeart} />
            </div>
            <div>Góp ý</div>
          </div>
          <div className="d-flex justify-content-between top_bar_item">
            <div className="me-2">
              <FontAwesomeIcon icon={faCircleUser} />
            </div>
            <div className="me-2">Username</div>
            <div>
              <FontAwesomeIcon icon={faChevronDown} />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default TopBar;
