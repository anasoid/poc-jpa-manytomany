import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_TIMESTAMP_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './product.reducer';

export const ProductDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const productEntity = useAppSelector(state => state.product.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productDetailsHeading">Product</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{productEntity.id}</dd>
          <dt>
            <span id="code">Code</span>
          </dt>
          <dd>{productEntity.code}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{productEntity.name}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{productEntity.description}</dd>
          <dt>
            <span id="modifiedDate">Modified Date</span>
          </dt>
          <dd>
            {productEntity.modifiedDate ? (
              <TextFormat value={productEntity.modifiedDate} type="date" format={APP_TIMESTAMP_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdDate">Created Date</span>
          </dt>
          <dd>
            {productEntity.createdDate ? <TextFormat value={productEntity.createdDate} type="date" format={APP_TIMESTAMP_FORMAT} /> : null}
          </dd>
          <dt>Catgories</dt>
          <dd>
            {productEntity.catgories
              ? productEntity.catgories.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.code}</a>
                    {productEntity.catgories && i === productEntity.catgories.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/product" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product/${productEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductDetail;
